package nc.impl.pu.m27.bp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.pu.m27.settlebill.rule.StockInfoUtil;
import nc.bs.pub.pf.PfUtilTools;
import nc.itf.pu.reference.ia.M27IAServices;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.ia.mi2.po.I2AdjustBackData;
import nc.vo.ia.bill.entity.base.AbstractBaseBill;
import nc.vo.ia.bill.entity.real.AbstractRealHeadVO;
import nc.vo.ia.mi2.entity.I2BillVO;
import nc.vo.ia.mi2.entity.I2ItemVO;
import nc.vo.ia.mi9.entity.I9BillVO;
import nc.vo.ia.mi9.entity.I9HeadVO;
import nc.vo.ia.mi9.entity.I9ItemVO;
import nc.vo.ia.mig.entity.IGBillVO;
import nc.vo.ia.mig.entity.IGHeadVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pu.est.util.EstTOIAUtil;
import nc.vo.pu.m27.entity.ICostfactorDiscountUtil;
import nc.vo.pu.m27.entity.SettleBillHeaderVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillItemVOUtil;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.enumeration.EnumMatchRowType;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pu.m4201.enumeration.EnumToIAFlag;
import nc.vo.pu.pub.constant.PUParaValue;
import nc.vo.pu.pub.constant.PUParaValue.po12;
import nc.vo.pu.pub.constant.PUParaValue.po13;
import nc.vo.pu.pub.util.ListUtil;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.bill.SplitBill;
import nc.vo.pubapp.calculator.formula.NumConvertRateFormula;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.scmpub.res.billtype.IABillType;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.so.pub.util.AggVOUtil;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>结算单传成本的所有处理
 * <li>...
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author wangyf
 * @time 2009-7-7 上午11:30:55
 */
public class SettleBillToIABP {

  /**
   * <p>
   * <b>本类主要完成以下功能：</b>
   * <ul>
   * <li>存放货物结算将到传送到IA的数据
   * <li>
   * <li>...
   * </ul>
   * <p>
   * <b>变更历史（可选）：</b>
   * <p>
   * XXX版本增加XXX的支持。
   * <p>
   * <p>
   * 
   * @version 本版本号
   * @since 上一版本号
   * @author wangyf
   * @time 2009-7-9 下午04:48:31
   */
  private class GoodsToIASet {

    // 对存货核算单进行处理: 相同表头的单据组合成一张单据
    // 存货核算单据来源单据头ID和暂估标志和单据类型唯一性组合

    /** I9入库调整单 */
    private ArrayList<SettleBillItemVO> m_listSettleToAdjustI9 =
        new ArrayList<SettleBillItemVO>();

    /** IG损益调整单 */
    private ArrayList<SettleBillItemVO> m_listSettleToAdjustIG =
        new ArrayList<SettleBillItemVO>();

    /** I2采购入库单 */
    private ArrayList<SettleBillItemVO> m_listSettleToI2 =
        new ArrayList<SettleBillItemVO>();

    /** 暂估回冲单 */
    private ArrayList<SettleBillItemVO> m_listSettleToRushEst =
        new ArrayList<SettleBillItemVO>();

    /** 最终传至I2的单据 */
    private SettleBillVO[] m_vosFinalI2 = null;

    /** 最终传至I9的单据 */
    private SettleBillVO[] m_vosFinalI9 = null;

    /** 最终传至IG的单据 */
    private SettleBillVO[] m_vosFinalIG = null;

    /** 最终暂估回冲期初暂估的单据 */
    private SettleBillItemVO[] m_vosFinalRushInitestItem = null;

    /** 最终暂估回冲采购入的单据 */
    private I2AdjustBackData[] m_vosFinalRushPurinData = null;

    /** 最终暂估回冲消耗汇总的单据 */
    private I2AdjustBackData[] m_vosFinalRushVoiconsumeData = null;

    public GoodsToIASet() {
      // 默认构造器
    }

    /***
     * 增加暂估回冲数据
     * <p>
     * <b>参数说明</b>
     * 
     * @param voItem <p>
     * @since 6.0
     * @author wangyf
     * @time 2010-6-1 下午02:24:30
     */
    public void addRushEstimateItems(SettleBillItemVO voItem) {
      this.m_listSettleToRushEst.add(voItem);
    }

    /***
     * 增加采购入
     * <p>
     * <b>使用示例:</b>
     * <p>
     * <b>参数说明</b>
     * 
     * @param voItem <p>
     * @author wangyf
     * @time 2009-7-10 下午01:32:38
     */
    public void addToI2Items(SettleBillItemVO voItem) {
      // 如果本次结算金额为零（如无发票结算金额为零）则不传存货
      if (MathTool.isZero(voItem.getNmoney())) {
        return;
      }
      this.m_listSettleToI2.add(voItem);
    }

    /***
     * 增加入库调整单
     * <p>
     * <b>参数说明</b>
     * 
     * @param voItem <p>
     * @since 6.0
     * @author wangyf
     * @time 2010-6-1 下午02:31:12
     */
    public void addToI9Items(SettleBillItemVO voItem) {
      this.m_listSettleToAdjustI9.add(voItem);
    }

    /***
     * 增加损益调整单
     * <p>
     * <b>参数说明</b>
     * 
     * @param voItem <p>
     * @since 6.0
     * @author wangyf
     * @time 2010-6-1 下午02:31:52
     */
    public void addToIGItems(SettleBillItemVO voItem) {
      this.m_listSettleToAdjustIG.add(voItem);
    }

    /**
     * 整理数据
     * <p>
     * <b>参数说明</b>
     * <p>
     * 
     * @since 6.0
     * @author wangyf
     * @time 2010-6-3 下午01:12:46
     */
    public void confirmFinalData() {

      this.confirmFinalToI2SettleBills();
      this.confirmFinalToI9SettleBills();
      this.confirmFinalToIGSettleBills();
      this.confirmFinalRushEstimateData();

    }

    /***
     * 得到回冲期初暂估的数据
     * <p>
     * <b>使用示例:</b>
     * <p>
     * <b>参数说明</b>
     * 
     * @return <p>
     * @author wangyf
     * @time 2009-7-13 上午10:14:45
     */
    public SettleBillItemVO[] getFinalRushInitestData() {
      return this.m_vosFinalRushInitestItem;
    }

    /***
     * 得到回冲采购入的数组
     * <p>
     * <b>使用示例:</b>
     * <p>
     * <b>参数说明</b>
     * 
     * @return <p>
     * @author wangyf
     * @time 2009-7-13 上午10:14:45
     */
    public I2AdjustBackData[] getFinalRushPurinEstItems() {
      return this.m_vosFinalRushPurinData;
    }

    /***
     * 得到回冲消耗汇总的数组
     * <p>
     * <b>使用示例:</b>
     * <p>
     * <b>参数说明</b>
     * 
     * @return <p>
     * @author wangyf
     * @time 2009-7-13 上午10:14:45
     */
    public I2AdjustBackData[] getFinalRushVoiconsumeEstData() {
      return this.m_vosFinalRushVoiconsumeData;
    }

    /**
     * 得到采购入的数组
     * <p>
     * <b>使用示例:</b>
     * <p>
     * <b>参数说明</b>
     * 
     * @return <p>
     * @author wangyf
     * @time 2009-7-13 上午10:14:45
     */
    public SettleBillVO[] getFinalToI2SettleBills() {
      return this.m_vosFinalI2;
    }

    /***
     * 传入库调整单的ITEM
     * <p>
     * <b>使用示例:</b>
     * <p>
     * <b>参数说明</b>
     * 
     * @return <p>
     * @author wangyf
     * @time 2010-3-30 下午04:28:47
     */
    public SettleBillVO[] getFinalToI9SettleBills() {
      return this.m_vosFinalI9;
    }

    /***
     * 传损益调整单的ITEM
     * <p>
     * <b>使用示例:</b>
     * <p>
     * <b>参数说明</b>
     * 
     * @return <p>
     * @author wangyf
     * @time 2010-3-30 下午04:29:13
     */
    public SettleBillVO[] getFinalToIGSettleBills() {
      return this.m_vosFinalIG;
    }

    private void calculateAssistNumForRush(I2AdjustBackData voRush,
        StockSettleVO voStock) {
      UFDouble ninnum = voStock.getNinnum();
      UFDouble nestnum = voStock.getNestnum();
      UFDouble nsettlenum = voRush.getNnum();
      // 如果暂估数量等于入库数量（入库一次暂估完成），并且暂估数量等于本次结算数量（结算一次回冲完成），直接取入库辅数量
      if (ninnum.equals(nestnum) && nestnum.equals(nsettlenum)) {
        voRush.setNastnum(voStock.getNinassistnum());
      }
      else {
        // 根据主数量换算辅数量
        ScaleUtils scale = ScaleUtils.getScaleUtilAtBS();
        String castunitid = voStock.getCastunitid();
        UFDouble nastnum =
            NumConvertRateFormula.calculateAssistNum(nsettlenum,
                voStock.getVchangerate(), castunitid, scale);
        voRush.setNastnum(nastnum);
      }
    }

    /***
     * 传调整单的ITEM，给入库调整单、损益调整单共用
     * <p>
     * <b>使用示例:</b>
     * <p>
     * <b>参数说明</b>
     * 
     * @return <p>
     * @author wangyf
     * @time 2010-3-30 下午04:28:47
     */
    private SettleBillVO[] confirmFinalAdjustSettleBills(
        ArrayList<SettleBillItemVO> listOrig) {

      if (ListUtil.isEmpty(listOrig)) {
        return null;
      }

      /*
       * 如果不是最后一次结算：比较当前的结算单价是否和暂估、确认单价相同，如果相同，则不需要传成本。
       * 如果结算是最后一次结算，且结算金额和剩余的暂估金额无差异，则不需补差。
       */
      SettleBillItemVO voItem = null;
      ArrayList<SettleBillItemVO> listRet = new ArrayList<SettleBillItemVO>();
      UFDouble dMoneyDiff = null;
      for (int i = 0; i < listOrig.size(); i++) {
        voItem = listOrig.get(i);
        // 暂估时以减暂估、确认时减确认
        if (MathTool.compareTo(voItem.getNoppoconfmmoney(), UFDouble.ZERO_DBL) != 0) {
          dMoneyDiff =
              MathTool
                  .sub(voItem.getNgoodsmoney(), voItem.getNoppoconfmmoney());
        }
        // 如果冲销暂估金额为零，但标记冲过暂估也要处理－这种情况出现在委外入无单价暂估等
        else if (MathTool.compareTo(voItem.getNclashestmoney(),
            UFDouble.ZERO_DBL) != 0
            || UFBoolean.TRUE.equals(voItem.getBwashest())) {
          dMoneyDiff =
              MathTool.sub(voItem.getNgoodsmoney(), voItem.getNclashestmoney());
        }
        else {// 如果没有暂估，也没有确认，则取合理损耗，用于生成IG
          dMoneyDiff = voItem.getNreasonalwastmny();
        }

        if (MathTool.compareTo(dMoneyDiff, UFDouble.ZERO_DBL) != 0) {
          try {
            // 组织差异VO
            SettleBillItemVO voAdjust = (SettleBillItemVO) voItem.clone();
            // 重置差异金额；去掉数量、单价、成本要素，其余的全部复制。
            voAdjust.setNmoney(dMoneyDiff);
            // voAdjust.setNsettlenum(null);
            voAdjust.setNprice(null);
            voAdjust.setNgoodsmoney(null);
            voAdjust.setNgoodsprice(null);
            for (int j = 0; j < CostfactorVO.MAX_NUM; j++) {
              ICostfactorDiscountUtil.setNcostfactor(voAdjust, j, null);
            }
            // 加入待传之行列
            listRet.add(voAdjust);
          }
          catch (Exception ex) {
            ExceptionUtils.wrappException(ex);
          }
        }
      }

      if (ListUtil.isEmpty(listRet)) {
        return null;
      }

      // -------------------分单
      // 采购入、VMI、期初暂估、委外入
      SettleBillVO voRet = new SettleBillVO();
      voRet.setParentVO((SettleBillHeaderVO) SettleBillToIABP.this.getBill()
          .getParentVO().clone());

      SettleBillItemVO[] voaBeforeSplit =
          listRet.toArray(new SettleBillItemVO[listRet.size()]);
      voRet.setChildrenVO(voaBeforeSplit);

      SplitBill<SettleBillVO> split = new SplitBill<SettleBillVO>();
      // 库存单据、成本域、供应商是默认分单条件
      split.appendKey(SettleBillItemVO.PK_COSTREGION);
      split.appendKey(SettleBillItemVO.PK_STOCK);
      split.appendKey(SettleBillItemVO.PK_SUPPLIER);
      SettleBillVO[] voaAfterBill = split.split(new SettleBillVO[] {
        voRet
      });

      return voaAfterBill;

    }

    /***
     * 得到暂估回冲的数组
     * <p>
     * <b>使用示例:</b>
     * <p>
     * <b>参数说明</b>
     * 
     * @return <p>
     * @author wangyf
     * @time 2009-7-13 上午10:14:45
     */
    private void confirmFinalRushEstimateData() {

      if (ListUtil.isEmpty(this.m_listSettleToRushEst)) {
        return;
      }

      ArrayList<I2AdjustBackData> listRushPurin =
          new ArrayList<I2AdjustBackData>();
      ArrayList<I2AdjustBackData> listRushVoiconsume =
          new ArrayList<I2AdjustBackData>();
      ArrayList<SettleBillItemVO> listRushInitest =
          new ArrayList<SettleBillItemVO>();

      for (SettleBillItemVO voSettleItem : this.m_listSettleToRushEst) {
        StockSettleVO voStock =
            SettleBillToIABP.this.getStockInfo().getStockSettleVO(
                voSettleItem.getPk_stock_b());
        // 得到库存单据VO，从库存单据生成回冲单
        if (POBillType.InitEstimate.getCode().equals(
            voSettleItem.getVstockbilltype())) {
          listRushInitest.add(voSettleItem);
        }
        else {
          I2AdjustBackData voRush = new I2AdjustBackData();
          voRush.setVsettlecode(SettleBillToIABP.this.getSettleBillVO()
              .getParentVO().getVbillcode());
          voRush.setCsettleid(voSettleItem.getPk_settlebill());
          voRush.setCsettlebid(voSettleItem.getPk_settlebill_b());
          voRush.setVsettlerowno(voSettleItem.getCrowno());
          voRush.setCicid(voSettleItem.getPk_stock());
          voRush.setCicbid(voSettleItem.getPk_stock_b());
          voRush.setNnum(voSettleItem.getNsettlenum());
          // wuxla v61
          // voRush.setNprice(voStock.getNestprice());
          voRush.setNprice(voStock.getNestcalcostprice());
          voRush.setNmny(voSettleItem.getNclashestmoney());
          voRush.setVchangerate(voStock.getVchangerate());

          // by 20141209 mengjian 取不到发票日期，取业务日期
          UFDate date = voSettleItem.getInvoicebilldate();
          if(date == null){
            date = AppContext.getInstance().getBusiDate();
          }
          voRush.setDaccountdate(date);

          this.calculateAssistNumForRush(voRush, voStock);

          // 加入待传之行列
          if (ICBillType.PurchaseIn.getCode().equals(
              voSettleItem.getVstockbilltype())) {
            listRushPurin.add(voRush);
          }
          else if (ICBillType.VmiSum.getCode().equals(
              voSettleItem.getVstockbilltype())) {
            listRushVoiconsume.add(voRush);
          }
        }
      }

      this.m_vosFinalRushPurinData =
          ListUtil.isEmpty(listRushPurin) ? null : listRushPurin
              .toArray(new I2AdjustBackData[listRushPurin.size()]);
      this.m_vosFinalRushVoiconsumeData =
          ListUtil.isEmpty(listRushVoiconsume) ? null : listRushVoiconsume
              .toArray(new I2AdjustBackData[listRushVoiconsume.size()]);
      this.m_vosFinalRushInitestItem =
          ListUtil.isEmpty(listRushInitest) ? null : listRushInitest
              .toArray(new SettleBillItemVO[listRushInitest.size()]);

    }

    /**
     * 方法功能描述：得到最终传I2单据的结算单VO数组
     * <p>
     * <b>参数说明</b>
     * 
     * @throws BusinessException <p>
     * @since 6.0
     * @author duy
     * @time 2010-8-11 下午02:36:37
     */
    private void confirmFinalToI2SettleBills() {

      if (ListUtil.isEmpty(this.m_listSettleToI2)) {
        return;
      }

      SettleBillVO vo = new SettleBillVO();
      vo.setParentVO((SettleBillHeaderVO) SettleBillToIABP.this.getBill()
          .getParentVO().clone());

      SettleBillItemVO[] voaBeforeSplit =
          new SettleBillItemVO[this.m_listSettleToI2.size()];
      for (int i = 0; i < voaBeforeSplit.length; i++) {
        voaBeforeSplit[i] =
            (SettleBillItemVO) this.m_listSettleToI2.get(i).clone();
        // I2传存货不考虑费用和折扣（后二者由调整单传入）
        voaBeforeSplit[i].setNprice(voaBeforeSplit[i].getNgoodsprice());
        voaBeforeSplit[i].setNmoney(voaBeforeSplit[i].getNgoodsmoney());
      }
      vo.setChildrenVO(voaBeforeSplit);

      SplitBill<SettleBillVO> split = new SplitBill<SettleBillVO>();
      // 库存单据、成本域是默认分单条件(可以覆盖按单据类型、库存单据、供应商、成本域分单）
      // 按会计期间和成本域分单是存货处理
      split.appendKey(SettleBillItemVO.PK_COSTREGION);
      split.appendKey(SettleBillItemVO.PK_STOCK);
      split.appendKey(SettleBillItemVO.PK_SUPPLIER);
      if (EnumMatchRowType.InvoiceDTransPO.toInteger().equals(
          voaBeforeSplit[0].getFrowtype())) {
        split.appendKey(SettleBillItemVO.PK_STORDOC);
      }
      SettleBillVO[] voaAfterBill = split.split(new SettleBillVO[] {
        vo
      });

      this.m_vosFinalI2 = voaAfterBill;
    }

    /***
     * 传入库调整单的ITEM
     * <p>
     * <b>使用示例:</b>
     * <p>
     * <b>参数说明</b>
     * 
     * @return <p>
     * @author wangyf
     * @time 2010-3-30 下午04:28:47
     */
    private void confirmFinalToI9SettleBills() {
      this.m_vosFinalI9 =
          this.confirmFinalAdjustSettleBills(this.m_listSettleToAdjustI9);
    }

    /***
     * 传损益调整单的ITEM
     * <p>
     * <b>使用示例:</b>
     * <p>
     * <b>参数说明</b>
     * 
     * @return <p>
     * @author wangyf
     * @time 2010-3-30 下午04:29:13
     */
    private void confirmFinalToIGSettleBills() {
      this.m_vosFinalIG =
          this.confirmFinalAdjustSettleBills(this.m_listSettleToAdjustIG);
    }

  }

  /** 结算单 */
  private SettleBillVO m_bill = null;

  /** 结算环境 */
  private SettleEnvironment m_settleEnv = null;

  /** 库存单据信息类 */
  private StockInfoUtil m_stockInfo = null;

  /** IA的单据集合 */
  private GoodsToIASet m_ToIASet = new GoodsToIASet();

  /** 当前结算单上物料是否传存货（物料价值管理属性） **/
  private Map<String, Map<String, UFBoolean>> orgToIAMarMap = null;

  public SettleBillToIABP(final SettleBillVO bill,
      final SettleEnvironment settleEnv) {
    this.m_bill = bill;
    this.m_settleEnv = settleEnv;

  }

  public SettleEnvironment getSettleEnv() {
    return this.m_settleEnv;
  }

  /**
   * 结算单传成本
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voBill 结算单VO数组
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2009-7-7 上午11:33:35
   */
  public void submitToIA(StockInfoUtil util) throws BusinessException {
    // 必定是同一个财务组织
    // 判断成本域是否启用
    boolean isIAEnabled = SysInitGroupQuery.isIAEnabled();
    if (!isIAEnabled) {
      return;
    }

    this.m_stockInfo = util;

    // 对传递到IA的表体进行分类
    this.classifyItems();

    // 整理所有的数据：没有差异的不传...，组织待传的数据
    this.orginazeFinalSettleVO();

    // 进行VO转换，转为IA单据；传递到IA
    this.convertAndPassToIA();

  }

  /**
   * 对确认成本进行调整，生成成本调整单
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voBillItem
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2009-7-9 下午04:16:32
   */
  private void adjustConfirmDataCost(SettleBillItemVO voBillItem) {
    this.getToIASet().addToI9Items(voBillItem);
  }

  /***
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param voBillItem
   * @throws BusinessException <p>
   * @since 6.0
   * @author wangyf
   * @time 2010-6-1 下午02:22:47
   */
  private void adjustConfirmDataProfitLoss(SettleBillItemVO voBillItem) {
    this.getToIASet().addToIGItems(voBillItem);
  }

  /**
   * 对暂估进行调整，生成成本调整单
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voBillItem
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2009-7-9 下午04:19:05
   */
  private void adjustEstimateDataCost(SettleBillItemVO voBillItem) {

    this.getToIASet().addToI9Items(voBillItem);

  }

  /**
   * 对暂估进行调整，生成损益调整单
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voBillItem
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2009-7-9 下午04:19:38
   */
  private void adjustEstimateDataProfitLoss(SettleBillItemVO voBillItem) {
    // 2012-07-19 tianft:暂估调整,清空合理损耗单价、数量、金额
    SettleBillItemVO cloneItem = (SettleBillItemVO) voBillItem.clone();
    cloneItem.setNreasonalwastmny(null);
    cloneItem.setNreasonalwastnum(null);
    cloneItem.setNreasonalwastprice(null);
    this.getToIASet().addToIGItems(cloneItem);
  }

  /**
   * 单据传送到IA
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2009-7-9 下午04:16:13
   */
  private void classifyItems() {

    for (SettleBillItemVO voSettleItem : this.getBill().getChildrenVO()) {
      Integer frowtype = voSettleItem.getFrowtype();
      StockSettleVO voStockItem =
          this.getStockInfo().getStockSettleVO(voSettleItem.getPk_stock_b());
      // 如果是入库单发票匹配、异物料结算之入库单，且必须影响成本才处理
      if ((EnumMatchRowType.StockInvoiceMatch.value().equals(frowtype) || EnumMatchRowType.StockInDiffMatch
          .value().equals(frowtype))
          && null != voStockItem
          && SettleBillItemVOUtil.isSettleToIA(voSettleItem, voStockItem)) {
        // 如果是采购入
        if (ICBillType.PurchaseIn.getCode().equals(
            voSettleItem.getVstockbilltype())) {

          // 如果未进行过暂估或确认成本。根据结算单数据生成存货采购入库单，即直接传成本
          Integer fdirtoiatype = voStockItem.getFdirtoiatype();
          if (EnumToIAFlag.NotToIA.value().equals(fdirtoiatype)) {
            this.directPurStock(voSettleItem);
          }
          // 如果库存单据进行过暂估成本
          else if (EnumToIAFlag.EstimateToIA.value().equals(fdirtoiatype)) {
            // 如果是回冲，则按暂估数据准备回冲数据，生成回冲单、正式存货入库单，即暂估回冲传成本。
            if (this.getParaEstProcessMode().equals(PUParaValue.po12.rush)) {
              this.rushAndConfirmEstimateData(voSettleItem);
            }
            else {
              // 如果是调差，根据暂估数据和结算单数据准备调差数据，生成存货调整单（成本或损益），即暂估调差传成本。
              if (this.getParaDiffMode().equals(PUParaValue.po13.cost)) {
                this.adjustEstimateDataCost(voSettleItem);
              }
              else {
                this.adjustEstimateDataProfitLoss(voSettleItem);
              }
            }
          }
          // 如果是确认成本，传调整单
          else if (EnumToIAFlag.ConfirmToIA.value().equals(fdirtoiatype)) {
            if (this.getParaDiffMode().equals(PUParaValue.po13.cost)) {
              this.adjustConfirmDataCost(voSettleItem);
            }
            else {
              this.adjustConfirmDataProfitLoss(voSettleItem);
            }
          }
        }
        // VMI(和采购入基本相似但只可能暂估，不存在确认)期初暂估单(一定有暂估无确认)
        else if (ICBillType.VmiSum.getCode().equals(
            voSettleItem.getVstockbilltype())
            || POBillType.InitEstimate.getCode().equals(
                voSettleItem.getVstockbilltype())) {
          // 如果未进行过暂估或确认成本。根据结算单数据生成存货采购入库单，即直接传成本
          Integer fdirtoiatype = voStockItem.getFdirtoiatype();
          if (EnumToIAFlag.NotToIA.value().equals(fdirtoiatype)) {
            this.directPurStock(voSettleItem);
          }
          else if (EnumToIAFlag.EstimateToIA.value().equals(fdirtoiatype)) {
            // 如果是回冲，则按暂估数据准备回冲数据，生成回冲单、正式存货入库单，即暂估回冲传成本。
            if (this.getParaEstProcessMode().equals(PUParaValue.po12.rush)) {
              this.rushAndConfirmEstimateData(voSettleItem);
            }
            else {
              // 如果是调差，根据暂估数据和结算单数据准备调差数据，生成存货调整单（成本或损益），即暂估调差传成本。
              if (this.getParaDiffMode().equals(PUParaValue.po13.cost)) {
                this.adjustEstimateDataCost(voSettleItem);
              }
              else {
                this.adjustEstimateDataProfitLoss(voSettleItem);
              }
            }
          }
        }
        // 库存单据类型如果是委外加工入库单。其已相当于暂估成本且成本调整方式只有调差。后续和采购入库单一致。
        else if (ICBillType.SubContinIn.getCode().equals(
            voSettleItem.getVstockbilltype())) {
          // 如果是调差，根据暂估数据和结算单数据准备调差数据，生成存货调整单（成本或损益），即暂估调差传成本。
          if (this.getParaDiffMode().equals(PUParaValue.po13.cost)) {
            this.adjustEstimateDataCost(voSettleItem);
          }
          else {
            this.adjustEstimateDataProfitLoss(voSettleItem);
          }
        }
        // 对合理损耗根据参数PO75生成损益调整单
        this.prepareReasonWasteStlItem(voSettleItem);
      }
      // 如果是“采购入库单红兰对冲”按暂估数据准备回冲数据，生成回冲单，即回冲传成本。
      else if (EnumMatchRowType.StockRush.value().equals(frowtype)) {
        if (null != voStockItem
            && SettleBillItemVOUtil.isSettleToIA(voSettleItem, voStockItem)
            && EnumToIAFlag.EstimateToIA.value().equals(
                voStockItem.getFdirtoiatype())) {
          this.rushEstimateData(voSettleItem);
        }
      }
      // 如果是“费用结算之入库单”（采购入、其他入、VMI、调拨入库单）根据结算单数据准备调差数据，生成存货调整单
      // 因结构调整，此分支走不到
      else if (EnumMatchRowType.StockFeeSettle.value().equals(frowtype)
          && null != voStockItem
          && SettleBillItemVOUtil.isSettleToIA(voSettleItem, voStockItem)) {
        if (this.getParaDiffMode().equals(PUParaValue.po13.cost)) {
          this.feeSettleCost(voSettleItem);
        }
        else {
          this.feeSettleProfitLoss(voSettleItem);
        }
      }
      // i) 如果是“直运无入库单之发票”
      // ---i. 生成存货核算采购入库单。调用SettleBillToIABP.directToIA()。
      else if (EnumMatchRowType.InvoiceDTransPO.value().equals(frowtype)) {
        this.invoiceDTransToIA(voSettleItem);
      }
    }

  }

  /**
   * 清空库存单据
   * <p>
   * 使用场景：VO交换时查询出库存信息，放在该字段上，然后执行VO交换。最后需要清空
   * <ul>
   * <li>
   * </ul>
   * 
   * @param settleBills
   */
  private void clearStockbill(SettleBillVO[] settleBills) {
    for (SettleBillVO settleBill : settleBills) {
      settleBill.getParentVO().setStockbill(null);
    }
  }

  /**
   * 转换为IA单据
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @author wangyf
   * @time 2009-7-13 上午09:51:37
   */
  private void convertAndPassToIA() throws BusinessException {
    // 回冲
    this.convertToRush();
    // 采购入
    this.convertToI2();
    // 入库调整
    this.convertToI9();
    // 损益调整
    this.convertToIG();
  }

  /**
   * 转换为采购入
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @author wangyf
   * @time 2009-7-13 上午10:02:20
   */
  private void convertToI2() throws BusinessException {
    SettleBillVO[] settleBills = this.getToIASet().getFinalToI2SettleBills();
    if (settleBills == null || settleBills.length == 0) {
      return;
    }
    // 执行真正的将I2传存货
    this.convertToI2(settleBills);

  }

  private void convertToI2(SettleBillVO[] settleBills) throws BusinessException {
    this.convertToI2(settleBills, false);
  }

  /**
   * 传存货
   * 
   * @param settleBills
   * @param forM4tRush 是否为期初的回冲用
   * @throws BusinessException
   */
  private void convertToI2(SettleBillVO[] settleBills, boolean forM4tRush)
      throws BusinessException {
    this.whenCrossFinanceOrg(settleBills);
    MapList<String, SettleBillVO> mapList = new MapList<String, SettleBillVO>();
    for (int i = 0; i < settleBills.length; ++i) {
      SettleBillItemVO item = settleBills[i].getChildrenVO()[0];
      String settleStockBillType = this.getSettleStockBillType(item);
      mapList.put(settleStockBillType, settleBills[i]);
    }
    List<I2BillVO> i2Lst = new ArrayList<I2BillVO>();
    for (Entry<String, List<SettleBillVO>> btstlentry : mapList.entrySet()) {
      SettleBillVO[] stlVos =
          btstlentry.getValue().toArray(
              new SettleBillVO[btstlentry.getValue().size()]);
      I2BillVO[] destI2Vos =
          (I2BillVO[]) PfUtilTools.runChangeDataAry(btstlentry.getKey(),
              IABillType.CGRK.getCode(), stlVos);
      EstTOIAUtil.adjustI2Price(destI2Vos);
      // 清空结算单上的计算属性：库存单据
      this.clearStockbill(stlVos);
      // 针对消耗汇总补充入库类型
      if (POBillType.VoiConsumeSettleBill.getCode().equals(btstlentry.getKey())) {
        this.setStockTranTypeForVMI(destI2Vos, settleBills[0].getParentVO()
            .getPk_org(), AbstractRealHeadVO.CTRANTYPEID);
      }
      i2Lst.addAll(Arrays.asList(destI2Vos));

    }
    I2BillVO[] voaI2 = i2Lst.toArray(new I2BillVO[i2Lst.size()]);
    // I2BillVO[] voaI2 = new I2BillVO[settleBills.length];
    // for (int i = 0; i < settleBills.length; i++) {
    // SettleBillItemVO item = settleBills[i].getChildrenVO()[0];
    // String settleStockBillType = this.getSettleStockBillType(item);
    //
    // // 转换
    // voaI2[i] =
    // (I2BillVO) PfUtilTools.runChangeData(settleStockBillType,
    // IABillType.CGRK.getCode(), settleBills[i]);
    // // 针对消耗汇总补充入库类型
    // if
    // (POBillType.VoiConsumeSettleBill.getCode().equals(settleStockBillType)) {
    // EstTOIAUtil.setStockTranTypeForVMI(voaI2[i], item.getPk_org(),
    // AbstractRealHeadVO.CTRANTYPEID);
    // }
    // }

    // 为存货核算的采购入库单补充辅数量
    this.fillI2Nastnum(voaI2);

    EstTOIAUtil.adjustI2Price(voaI2);
    if (forM4tRush) {
      M27IAServices.insertI2ForPOQCSettleAdjustBack(voaI2);
    }
    else {
      // 传至IA
      M27IAServices.settleToI2(voaI2);
    }
  }

  /**
   * 转换为入库调整单
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @author wangyf
   * @time 2009-7-13 上午10:02:20
   */
  private void convertToI9() throws BusinessException {
    SettleBillVO[] settleBills = this.getToIASet().getFinalToI9SettleBills();
    if (settleBills == null || settleBills.length == 0) {
      return;
    }

    this.whenCrossFinanceOrg(settleBills);

    // I9BillVO[] voaI9 = new I9BillVO[settleBills.length];
    // for (int i = 0; i < settleBills.length; i++) {
    // SettleBillItemVO item = settleBills[i].getChildrenVO()[0];
    // String settleStockBillType = this.getSettleStockBillType(item);
    //
    // // 转换
    // voaI9[i] =
    // (I9BillVO) PfUtilTools.runChangeData(settleStockBillType,
    // IABillType.RKTZ.getCode(), settleBills[i]);
    // // 针对消耗汇总补充入库类型
    // if
    // (POBillType.VoiConsumeSettleBill.getCode().equals(settleStockBillType)) {
    // EstTOIAUtil.setStockTranTypeForVMI(voaI9[i], item.getPk_org(),
    // I9HeadVO.CTRANTYPEID);
    // }
    // }
    MapList<String, SettleBillVO> mapList = new MapList<String, SettleBillVO>();
    for (int i = 0; i < settleBills.length; ++i) {
      SettleBillItemVO item = settleBills[i].getChildrenVO()[0];
      String settleStockBillType = this.getSettleStockBillType(item);
      mapList.put(settleStockBillType, settleBills[i]);
    }
    List<I9BillVO> i9List = new ArrayList<I9BillVO>();
    for (Entry<String, List<SettleBillVO>> btstlentry : mapList.entrySet()) {
      SettleBillVO[] stlVos =
          btstlentry.getValue().toArray(
              new SettleBillVO[btstlentry.getValue().size()]);
      I9BillVO[] destI9Vos =
          (I9BillVO[]) PfUtilTools.runChangeDataAry(btstlentry.getKey(),
              IABillType.RKTZ.getCode(), stlVos);

      // 清空结算单上的计算属性：库存单据
      this.clearStockbill(stlVos);

      if (POBillType.VoiConsumeSettleBill.getCode().equals(btstlentry.getKey())) {
        this.setStockTranTypeForVMI(destI9Vos, settleBills[0].getParentVO()
            .getPk_org(), I9HeadVO.CTRANTYPEID);
      }
      i9List.addAll(Arrays.asList(destI9Vos));
    }
    I9BillVO[] voaI9 = i9List.toArray(new I9BillVO[i9List.size()]);

    // 传至IA
    M27IAServices.settleToI9(this.setAdjustSrcInfo(voaI9, settleBills));
  }

  /**
   * 转换为损益调整单
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @author wangyf
   * @time 2009-7-13 上午10:02:58
   */
  private void convertToIG() throws BusinessException {
    SettleBillVO[] settleBills = this.getToIASet().getFinalToIGSettleBills();
    if (settleBills == null || settleBills.length == 0) {
      return;
    }

    this.whenCrossFinanceOrg(settleBills);

    // IGBillVO[] voaIG = new IGBillVO[settleBills.length];
    // for (int i = 0; i < settleBills.length; i++) {
    // SettleBillItemVO item = settleBills[i].getChildrenVO()[0];
    // String settleStockBillType = this.getSettleStockBillType(item);
    //
    // // 转换
    // voaIG[i] =
    // (IGBillVO) PfUtilTools.runChangeData(settleStockBillType,
    // IABillType.SYTZ.getCode(), settleBills[i]);
    // // 针对消耗汇总补充入库类型
    // if
    // (POBillType.VoiConsumeSettleBill.getCode().equals(settleStockBillType)) {
    // EstTOIAUtil.setStockTranTypeForVMI(voaIG[i], item.getPk_org(),
    // IGHeadVO.CTRANTYPEID);
    // }
    // }
    MapList<String, SettleBillVO> mapList = new MapList<String, SettleBillVO>();
    for (int i = 0; i < settleBills.length; ++i) {
      SettleBillItemVO item = settleBills[i].getChildrenVO()[0];
      String settleStockBillType = this.getSettleStockBillType(item);
      mapList.put(settleStockBillType, settleBills[i]);
    }

    List<IGBillVO> igList = new ArrayList<IGBillVO>();
    for (Entry<String, List<SettleBillVO>> btstlentry : mapList.entrySet()) {
      SettleBillVO[] stlVos =
          btstlentry.getValue().toArray(
              new SettleBillVO[btstlentry.getValue().size()]);
      IGBillVO[] destIGVos =
          (IGBillVO[]) PfUtilTools.runChangeDataAry(btstlentry.getKey(),
              IABillType.SYTZ.getCode(), stlVos);

      // 清空结算单上的计算属性：库存单据
      this.clearStockbill(stlVos);

      if (POBillType.VoiConsumeSettleBill.getCode().equals(btstlentry.getKey())) {
        this.setStockTranTypeForVMI(destIGVos, settleBills[0].getParentVO()
            .getPk_org(), IGHeadVO.CTRANTYPEID);
      }
      igList.addAll(Arrays.asList(destIGVos));
    }
    IGBillVO[] voaIG = igList.toArray(new IGBillVO[igList.size()]);

    // 传至IA
    M27IAServices.settleToIG(voaIG);
  }

  /**
   * 转换为暂估回冲单
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @author wangyf
   * @time 2009-7-13 上午10:03:37
   */
  private void convertToRush() throws BusinessException {

    // 回冲采购入
    if (!ArrayUtils.isEmpty(this.getToIASet().getFinalRushPurinEstItems())) {

      I2AdjustBackData[] adjustData =
          this.getToIASet().getFinalRushPurinEstItems();
      // 回冲数据单价的处理
      EstTOIAUtil.adjustI2Price(adjustData, this.m_bill.getParentVO()
          .getCcurrencyid());
      M27IAServices.settleToI2ForAdjustBack(adjustData);
    }

    // 回冲消耗汇总
    if (!ArrayUtils.isEmpty(this.getToIASet().getFinalRushVoiconsumeEstData())) {
      I2AdjustBackData[] adjustData =
          this.getToIASet().getFinalRushVoiconsumeEstData();
      // 回冲数据单价的处理
      EstTOIAUtil.adjustI2Price(adjustData, this.m_bill.getParentVO()
          .getCcurrencyid());
      M27IAServices.settleToI2ForAdjustBack(adjustData);
    }

    // 期初暂估的回冲数据（只能走VO对照，因存货中没有期初的暂估数据）
    SettleBillItemVO[] finalRushInitestData =
        this.getToIASet().getFinalRushInitestData();
    if (!ArrayUtils.isEmpty(finalRushInitestData)) {
      SettleBillItemVO[] cloneItems =
          new SettleBillItemVO[finalRushInitestData.length];
      for (int i = 0; i < finalRushInitestData.length; ++i) {
        cloneItems[i] = (SettleBillItemVO) finalRushInitestData[i].clone();
        this.opposeInitEstSettleItem(cloneItems[i]);
      }
      SettleBillVO vo = new SettleBillVO();
      vo.setParent((ISuperVO) this.getBill().getParent().clone());
      vo.setChildrenVO(cloneItems);

      // wuxla、王印芬、孙聪、杨永伟、秦刚 2013-6-17
      // 合生元项目问题，两张不同入库仓库的暂估入库单合并开票后，生成的存货单据仓库只有一个，合生元出现存货核算收发存汇总表按仓库维度查询收发数据错误
      // 王姐的回复如下：不能通过对不同仓库分单开发票解决。
      // 我看了下6的采购需求，结算传存货分单规则是：按照单据类型+会计期间+成本域+库存单据号分单。应该按期初暂估单分单，这样就不会有仓库不对的问题。
      // 所以先将结算单按照期初暂估单分单，再VO对照成I2，因为没有分单规则，所以不会出问题。
      SettleBillVO[] initrushsettlevos =
          this.splitSettleForInitEst(new SettleBillVO[] {
            vo
          });

      // 执行传存货
      this.convertToI2(initrushsettlevos, true);
    }

  }

  /**
   * 直接传成本
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voBillItem
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2009-7-9 下午04:19:50
   */
  private void directPurStock(SettleBillItemVO voBillItem) {
    this.getToIASet().addToI2Items(voBillItem);
  }

  /**
   * 费用结算传成本调整单
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voBillItem
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2009-7-9 下午04:20:01
   */
  private void feeSettleCost(SettleBillItemVO voBillItem) {
    //
  }

  /**
   * 费用结算传损益调整单
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voBillItem
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2009-7-9 下午04:20:17
   */
  private void feeSettleProfitLoss(SettleBillItemVO voBillItem) {
    //
  }

  /**
   * 方法功能描述：为存货核算的采购入库单补充辅数量
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos <p>
   * @since 6.0
   * @author duy
   * @time 2010-8-11 下午04:46:56
   */
  private void fillI2Nastnum(I2BillVO[] vos) {
    ScaleUtils scale = ScaleUtils.getScaleUtilAtBS();
    for (I2BillVO vo : vos) {
      I2ItemVO[] items = vo.getChildrenVO();
      for (I2ItemVO item : items) {
        UFDouble mainNum = item.getNnum();
        String convertRate = item.getVchangerate();
        String cunit = item.getCastunitid();
        // 单位不存在时处理成跟主单位一致
        if (StringUtils.isEmpty(cunit)) {
          item.setCastunitid(item.getCunitid());
          item.setNastnum(mainNum);
          item.setVchangerate(scale.adjustHslScale("1.00/1.00"));
          continue;
        }
        UFDouble nastnum =
            NumConvertRateFormula.calculateAssistNum(mainNum, convertRate,
                cunit, scale);
        item.setNastnum(nastnum);
      }
    }
  }

  private Map<String, Map<String, UFBoolean>> getOrgToIAMarMap() {
    if (null == this.orgToIAMarMap) {
      List<SettleBillVO> volst = new ArrayList<SettleBillVO>();
      volst.add(this.getBill());
      this.orgToIAMarMap = SettleBillItemVOUtil.getEffectByMaterial(volst);
    }
    return this.orgToIAMarMap;
  }

  /**
   * 得到差异转入方式
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param sFinanceOrgId
   * @return <p>
   * @author wangyf
   * @time 2010-1-4 下午02:06:32
   */
  private po13 getParaDiffMode() {
    return PUSysParamUtil.getPO13(this.getBill().getParentVO().getPk_org());
  }

  /**
   * 得到暂估调整方式
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param sFinanceOrgId
   * @return <p>
   * @author wangyf
   * @time 2010-1-4 下午02:05:49
   */
  private po12 getParaEstProcessMode() {
    return PUSysParamUtil.getPO12(this.getBill().getParentVO().getPk_org());
  }

  /**
   * 合理损耗是否进成本
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param sFinanceOrgId
   * @return <p>
   * @author wangyf
   * @time 2010-1-4 下午02:09:57
   */
  private boolean getParaWastInCost() {
    return PUSysParamUtil.getPO75(this.getBill().getParentVO().getPk_org())
        .booleanValue();
  }

  private String getSettleStockBillType(SettleBillItemVO item) {
    String settleStockBillType = null;
    String stockBillType = item.getVstockbilltype();
    // 根据来源单据得到不同的结算单单据类型
    if (!StringUtil.isEmpty(stockBillType)) {
      if (ICBillType.PurchaseIn.getCode().equals(stockBillType)) {
        settleStockBillType = POBillType.PurchaseInSettleBill.getCode();
      }
      else if (ICBillType.VmiSum.getCode().equals(stockBillType)) {
        settleStockBillType = POBillType.VoiConsumeSettleBill.getCode();
      }
      else if (POBillType.InitEstimate.getCode().equals(stockBillType)) {
        settleStockBillType = POBillType.InitialEstSettleBill.getCode();
      }
      else if (ICBillType.SubContinIn.getCode().equals(stockBillType)) {
        settleStockBillType = POBillType.SubContractSettleBill.getCode();
      }
    }
    else {
      settleStockBillType = POBillType.DTransNoPurchaseInSettleBill.getCode();
    }
    return settleStockBillType;
  }

  /**
   * IA单据的GET方法
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author wangyf
   * @time 2009-7-13 上午09:42:39
   */
  private GoodsToIASet getToIASet() {
    return this.m_ToIASet;
  }

  private void invoiceDTransToIA(SettleBillItemVO item) {
    Map<String, Map<String, UFBoolean>> orgMarMap = this.getOrgToIAMarMap();
    String pk_org = item.getPk_org();
    Map<String, UFBoolean> marMap = orgMarMap.get(pk_org);
    UFBoolean toia = marMap.get(item.getPk_material());
    // 只有物料价值管理是传存货的才处理，直运无入库这里要特殊处理一下，V61考虑将标志放到结算单
    if (!UFBoolean.FALSE.equals(toia)) {
      this.directPurStock(item);
    }
  }

  private void opposeInitEstSettleItem(SettleBillItemVO item) {
    item.setNsettlenum(MathTool.oppose(item.getNsettlenum()));
    item.setNmoney(MathTool.oppose(item.getNclashestmoney()));// 使用回冲暂估金额

    // wuxla v61
    // item.setNprice(this.getStockInfo().getStockSettleVO(item.getPk_stock_b())
    // .getNestprice());// 作用暂估单价
    item.setNprice(this.getStockInfo().getStockSettleVO(item.getPk_stock_b())
        .getNestcalcostprice());// 作用暂估单价
  }

  /**
   * 整理需传递到IA的所有数据
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wangyf
   * @time 2010-6-3 下午01:09:13
   */
  private void orginazeFinalSettleVO() {
    this.getToIASet().confirmFinalData();
  }

  /**
   * 将结算单行上的合理损耗根据PO75参数，准备好生成IG的数据
   * 
   * @param item
   */
  private void prepareReasonWasteStlItem(SettleBillItemVO item) {
    /**
     * V502 调整， FENGPING， 2007-12-27 <br>
     * 4.8 采购结算形成的合理损耗数量不进入成本的方案调整<br>
     * 目前产品处理时，合理损耗如果不进入成本，在已暂估并且为单到补差时， 合理损耗部分体现在损益调整单中，其他情况则体现在存货采购入库单上。<br>
     * 如合理损耗不进成本，应统一体现在损益调整单上，用户配置模板时也不会困惑，产品也更清晰。 <br>
     * 解决方案 <br>
     * 1、存货核算采购入库单去掉“合理损耗数量”、“合理损耗金额”字段。 <br>
     * 2、采购结算时，参数“采购结算时合理损耗是否进入成本”=“是”，
     * 则结算后生成的存货采购入库单或者入库调整单成本金额包括合理损耗金额，和原有处理一致。<br>
     * 3、采购结算时，参数“采购结算时合理损耗是否进入成本”=“否”， <br>
     * 则结算后生成的存货采购入库单或者入库调整单成本金额不包括合理损耗金额， <br>
     * 并且不再将合理损耗数量、合理损耗金额传递到存货采购入库单和入库调整单；
     * 合理损耗部分生成损益调整单，损益调整单上的合理损耗数量、合理损耗金额为结算合理损耗数量和金额。
     */
    if (this.getParaWastInCost() || MathTool.isZero(item.getNreasonalwastnum())) {
      return;
    }
    SettleBillItemVO cloneItem = (SettleBillItemVO) item.clone();
    cloneItem.setNclashestmoney(null);
    cloneItem.setNoppoconfmapmny(null);
    cloneItem.setNoppoconfmmoney(null);
    cloneItem.setBwashest(UFBoolean.FALSE);
    this.getToIASet().addToIGItems(cloneItem);

  }

  /**
   * 暂估回冲
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voBillItem
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2009-7-9 下午04:20:40
   */
  private void rushAndConfirmEstimateData(SettleBillItemVO voBillItem) {
    // 记入回冲的ITEM。
    this.getToIASet().addRushEstimateItems(voBillItem);
    // 记入传成本的ITEM。
    this.getToIASet().addToI2Items(voBillItem);
  }

  private void rushEstimateData(SettleBillItemVO voBillItem) {
    // 记入回冲的ITEM
    this.getToIASet().addRushEstimateItems(voBillItem);
  }

  private I9BillVO[] setAdjustSrcInfo(I9BillVO[] i9s, SettleBillVO[] SettleBills) {
    Map<String, SettleBillItemVO> settleBillItemMap =
        AggVOUtil.createItemMap(SettleBills);
    for (I9BillVO i9 : i9s) {
      for (I9ItemVO item : i9.getChildrenVO()) {
        String settleItemId = item.getCsrcbid();
        if (settleBillItemMap.containsKey(settleItemId)) {
          SettleBillItemVO settleItem = settleBillItemMap.get(settleItemId);
          if (StringUtils.isEmpty(item.getCadjustbillid())) {
            item.setCadjustbillid(settleItem.getPk_stock());
          }
          if (StringUtils.isEmpty(item.getCadjustbillitemid())) {
            item.setCadjustbillitemid(settleItem.getPk_stock_b());
          }
          if (MathTool.isZero(item.getNadjustnum())) {
            item.setNadjustnum(settleItem.getNsettlenum());
          }
        }
      }
    }
    return i9s;

  }

  private void setStockTranTypeForVMI(AbstractBaseBill[] iaBills,
      String pk_org, String iatrantypename) {
    String stocTranType = PUSysParamUtil.getPO54(pk_org);
    for (AbstractBaseBill iaBill : iaBills) {
      if (!StringUtils.isEmpty(stocTranType)) {
        iaBill.getParentVO().setAttributeValue(iatrantypename, stocTranType);
      }
    }
  }

  private SettleBillVO[] splitSettleForInitEst(SettleBillVO[] stlVos) {
    SplitBill<SettleBillVO> split = new SplitBill<SettleBillVO>();
    split.appendKey(SettleBillItemVO.PK_STOCK);
    split.appendKey(SettleBillItemVO.PK_COSTREGION);
    split.appendKey(SettleBillItemVO.PK_SUPPLIER);
    return split.split(stlVos);
  }

  /**
   * 如果结算财务组织和结算单表体的收货库存组织所属的财务组织不同，则属于分收集结，需要清空库存组织
   * 
   * @param settleBills 结算完毕的结算单
   */
  private void whenCrossFinanceOrg(SettleBillVO[] settleBills) {
    for (SettleBillVO settleBill : settleBills) {
      SettleBillItemVO[] items = settleBill.getChildrenVO();
      for (SettleBillItemVO item : items) {
        String pk_stock_b = item.getPk_stock_b();
        if (StringUtils.isNotBlank(pk_stock_b)) {
          SettleBillItemVOUtil.clearCenterPurInfo(item, this.getStockInfo()
              .getStockSettleVO(pk_stock_b));
        }
      }
    }
  }

  SettleBillVO getBill() {
    return this.m_bill;
  }

  SettleBillVO getSettleBillVO() {
    return this.m_bill;
  }

  StockInfoUtil getStockInfo() {
    return this.m_stockInfo;
  }

}
