package nc.pubimpl.pu.it.bp;

import nc.bs.pu.m27.settlebill.rule.WriteM5801AndM5805Rule;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.enumeration.EnumSettleType;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pu.m27.rule.SettleBillScaleCheckRule;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.BusinessException;
import nc.vo.scmpub.rule.FinanceOrgEnableCheckRule;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;

import nc.bs.pu.est.m45.rule.settle.PurchsInSettleUpdateRule;
import nc.bs.pu.m27.plugin.SettlebillPluginPoint;
import nc.bs.pu.m27.settlebill.rule.AuditInfoRule;
import nc.bs.pu.m27.settlebill.rule.BackWashEstAPForRBStockRush;
import nc.bs.pu.m27.settlebill.rule.BillCodeRule;
import nc.bs.pu.m27.settlebill.rule.FillOrgCurrencyRule;
import nc.bs.pu.m27.settlebill.rule.FillUnitidRule;
import nc.bs.pu.m27.settlebill.rule.OrgInfoFillRule;
import nc.bs.pu.m27.settlebill.rule.RefCostPriceUpdateRule;
import nc.bs.pu.m27.settlebill.rule.RowNoRule;
import nc.bs.pu.m27.settlebill.rule.SettleBillVOCheckRule;
import nc.bs.pu.m27.settlebill.rule.StockInfoUtil;
import nc.bs.pu.m27.settlebill.rule.ToIAStatusRule;
import nc.bs.pu.m27.settlebill.rule.WritebackInvoiceRule;
import nc.bs.pu.m27.settlebill.rule.WritebackOrderRule;
import nc.bs.pu.m27.settlebill.rule.WritebackPoint;

import nc.impl.pu.m27.bp.SettleMakeInvoiceToAP;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;

import nc.pubimpl.pu.it.rule.FeeSettleForGoodsRuleForIT;

/**
 * @description
 * 
 * 
 * 
 * @since 6.36
 * @version 2015-3-18 下午3:11:58
 * @author yixl
 */
public class SettleBillBPForIT {

  /**
   * 保存结算单
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @author wangyf
   * @param vos
   * @param settleEnv
   * @return SettleBillVO
   * @throws BusinessException
   * @time 2009-6-29 下午01:48:20
   */
  public SettleBillVO[] saveSettleBills(SettleBillVO[] vos,
      SettleEnvironment settleEnv) throws BusinessException {
    if (vos == null) {
      return null;
    }

    // 是否传成本
    boolean bToIA =
        this.isAutoToIA(settleEnv, vos[0].getParentVO().getPk_org());
    // 还需判断IA模块是否启用
    if (bToIA) {
      bToIA = SysInitGroupQuery.isIAEnabled();
    }

    StockInfoUtil util = new StockInfoUtil(vos);

    AroundProcesser<SettleBillVO> processor =
        new AroundProcesser<SettleBillVO>(SettlebillPluginPoint.SAVE_IT);

    // 增加保存前规则：单据号处理
    processor.addBeforeFinalRule(new BillCodeRule());
    // 增加保存前规则：审计信息
    processor.addBeforeFinalRule(new AuditInfoRule());
    // 增加保存前规则：财务组织版本信息补充
    processor.addBeforeFinalRule(new OrgInfoFillRule());
    // 补全组织本位币
    processor.addBeforeFinalRule(new FillOrgCurrencyRule());
    // 主组织停用检查
    processor.addBeforeRule(new FinanceOrgEnableCheckRule<SettleBillVO>());
    // 补全物料主单位
    processor.addBeforeFinalRule(new FillUnitidRule());
    // 增加保存前规则：补全行号
    processor.addBeforeFinalRule(new RowNoRule());
    // 保存前规则：vo校验
    processor.addBeforeFinalRule(new SettleBillVOCheckRule());
    // 增加保存前规则：是否传成本的标识设置
    processor.addBeforeFinalRule(new ToIAStatusRule(bToIA, false));
    // 增加保存后规则：红蓝入库单对冲时回冲暂估应付
    processor.addAfterFinalRule(new BackWashEstAPForRBStockRush(util));
    // 增加保存后规则：回写采购入财务
    processor.addAfterFinalRule(new PurchsInSettleUpdateRule(
        WritebackPoint.SAVE));
    // 增加保存后规则：回写发票
    processor.addAfterFinalRule(new WritebackInvoiceRule(WritebackPoint.SAVE));
    // 增加保存后规则：无来源订单的发票跟来源于订单的入库单结算时回写订单的累计开票数量和累计开票金额
    processor.addAfterFinalRule(new WritebackOrderRule(WritebackPoint.SAVE,
        settleEnv));
    // 无来源明细单的发票跟来源于明细单的入库单结算时回写进口明细单、进口合同
    processor.addAfterFinalRule(new WriteM5801AndM5805Rule(WritebackPoint.SAVE,
        settleEnv));

    // 更新参考成本
    processor.addAfterFinalRule(new RefCostPriceUpdateRule(bToIA));
    // 精度检查
    processor.addAfterFinalRule(new SettleBillScaleCheckRule());
    processor.before(vos);
    for (SettleBillVO settleVO : vos) {

      // 21.采购结算时采购入库单未结算数量中有部分已暂估，则结算时优先匹配已暂估数量，
      // 并记录已暂估数量中本次结算的数量，一方面以便在对应的采购发票传应付时
      // ，根据该采购入库单是否进行过暂估应付来确定是否回冲对应的暂估应付
      // ；同时另一方面采购结算传存货核算时针对暂估部分进行成本的回冲或者补差（根据暂估处理方式参数）。
      BillInsert<SettleBillVO> billInsertAction =
          new BillInsert<SettleBillVO>();
      billInsertAction.insert(new SettleBillVO[] {
        settleVO
      });

      // WYF 自动结算生成匹配状态的采购结算单后根据参数“自动结算生成采购结算单是否自动传存货核算”确定是自动传存货核算（参数值为是
      // ），还是用户在采购结算单节点手工传存货（参数值为否），是否放在SettleEnv中传入。
      // 传成本
      if (bToIA) {
        new SettleBillToIABPForIT(settleVO, settleEnv).submitToIA(util);
      }
    }
    // 进行完货物结后，再进行费用结算，一定放到最后一个规则处理
    processor.addAfterFinalRule(new FeeSettleForGoodsRuleForIT(settleEnv));

    processor.after(vos);

    // 导致发票传应付
    new SettleMakeInvoiceToAP(vos).makeInvoiceToAP();

    // WYF 需要重新组织数据
    return vos;
  }

  /**
   * 方法功能描述：是否自动传成本
   * <p>
   * <b>参数说明</b>
   * 
   * @param settleEnv 结算环境
   * @return 自动传成本，返回true，否则返回false
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-6-17 上午01:31:46
   */
  private boolean isAutoToIA(SettleEnvironment settleEnv, String pk_org) {
    if (settleEnv.getSettleType() == EnumSettleType.IT_UI_AUTO
        || settleEnv.getSettleType() == EnumSettleType.IT_INVOICE_AUTO) {
      return PUSysParamUtil.getPO87(pk_org);
    }
    return true;
  }
}
