package nc.pubitf.pu.m21.ec;

import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * @since 6.0
 * @version 2011-5-16 下午02:40:16
 * @author wuxla
 */

public class OrderItemApprovedVO extends AbstractDataView {

  public static class OrderItemApprovedVOMeta extends DataViewMeta {
    public OrderItemApprovedVOMeta() {
      this.init();
    }

    private void init() {
      /**
       * 订单主键、订单子表主键、合同号、物料id、自由辅助属性id、批次号、报价单位id、报价数量、单位id、数量、含税 单价
       * 折扣率(%)、净含税单价、价税合计、税率（%）、税额、币种id
       * 计划到货日期、供应商交货状态id、运输方式id、收货地址id、生产厂商id、质量等级id、主含税单价、主单位
       * mengjian by 20150124 新增：结算财务组织，收货组织，收货仓库，需求部门
       */
      // 项目、生产厂商、客户
      this.add(OrderItemVO.class, new String[] {
        OrderItemVO.PK_ORDER, OrderItemVO.PK_ORDER_B,
        OrderItemVO.VCONTRACTCODE, OrderItemVO.PK_MATERIAL, OrderItemVO.VFREE1,
        OrderItemVO.VFREE2, OrderItemVO.VFREE3, OrderItemVO.VFREE4,
        OrderItemVO.VFREE5, OrderItemVO.VFREE6, OrderItemVO.VFREE7,
        OrderItemVO.VFREE8, OrderItemVO.VFREE9, OrderItemVO.VFREE10,
        OrderItemVO.VBATCHCODE, OrderItemVO.CQTUNITID, OrderItemVO.NQTUNITNUM,
        OrderItemVO.CASTUNITID, OrderItemVO.NASTNUM,
        OrderItemVO.NQTORIGTAXPRICE, OrderItemVO.NITEMDISCOUNTRATE,
        OrderItemVO.NORIGORDERPRICE, OrderItemVO.NORIGTAXMNY,
        OrderItemVO.NTAXRATE, OrderItemVO.NTAX, OrderItemVO.CORIGCURRENCYID,
        OrderItemVO.DPLANARRVDATE, OrderItemVO.ISTORESTATUS,
        OrderItemVO.PK_RECEIVEADDRESS, OrderItemVO.CPRODUCTORID,
        OrderItemVO.CQUALITYLEVELID, OrderItemVO.TS, OrderItemVO.NORIGTAXPRICE,
        OrderItemVO.CUNITID, OrderItemVO.NQTORIGTAXNETPRC,
        OrderItemVO.CPROJECTID, OrderItemVO.CASSCUSTID,
        OrderItemVO.PK_PSFINANCEORG, OrderItemVO.PK_PSFINANCEORG_V,
        OrderItemVO.PK_RECVSTORDOC, OrderItemVO.PK_ARRVSTOORG,
        OrderItemVO.PK_ARRVSTOORG_V, OrderItemVO.PK_REQDEPT_V,
        OrderItemVO.PK_REQDEPT
      });
    }
  }

  private static final long serialVersionUID = -4530415669395963604L;

  /** 客户 getter 方法 */
  public String getCasscustid() {
    return (String) this.getAttributeValue(OrderItemVO.CASSCUSTID);
  }

  /** 单位 getter 方法 */
  public String getCastunitid() {
    return (String) this.getAttributeValue(OrderItemVO.CASTUNITID);
  }

  /** 币种 getter 方法 */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(OrderItemVO.CORIGCURRENCYID);
  }

  /** 生产厂商 getter 方法 */
  public String getCproductorid() {
    return (String) this.getAttributeValue(OrderItemVO.CPRODUCTORID);
  }

  /** 项目 getter 方法 */
  public String getCprojectid() {
    return (String) this.getAttributeValue(OrderItemVO.CPROJECTID);
  }

  /** 报价单位 getter 方法 */
  public String getCqtunitid() {
    return (String) this.getAttributeValue(OrderItemVO.CQTUNITID);
  }

  /** 质量等级 getter 方法 */
  public String getCqualitylevelid() {
    return (String) this.getAttributeValue(OrderItemVO.CQUALITYLEVELID);
  }

  /** 主单位 getter 方法 */
  public String getCunitid() {
    return (String) this.getAttributeValue(OrderItemVO.CUNITID);
  }

  /** 计划到货日期 getter 方法 */
  public UFDate getDplanarrvdate() {
    return (UFDate) this.getAttributeValue(OrderItemVO.DPLANARRVDATE);
  }

  /** 供应商交货状态 getter 方法 */
  public Integer getIstorestatus() {
    return (Integer) this.getAttributeValue(OrderItemVO.ISTORESTATUS);
  }

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getDataViewMeta(
        OrderItemApprovedVOMeta.class);
  }

  /** 数量 getter 方法 */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NASTNUM);
  }

  /** 折扣 getter 方法 */
  public UFDouble getNitemdiscountrate() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NITEMDISCOUNTRATE);
  }

  /** 净含税单价 getter 方法 */
  public UFDouble getNorigorderprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NORIGORDERPRICE);
  }

  /** 价税合计 getter 方法 */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NORIGTAXMNY);
  }

  /** 主含税单价 getter 方法 */
  public UFDouble getNorigtaxprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NORIGTAXPRICE);
  }

  /** 报价含税净价 getter 方法 */
  public UFDouble getNqtorigtaxnetprc() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NQTORIGTAXNETPRC);
  }

  /** 含税单价 getter 方法 */
  public UFDouble getNqtorigtaxprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NQTORIGTAXPRICE);
  }

  /** 报价数量 getter 方法 */
  public UFDouble getNqtunitnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NQTUNITNUM);
  }

  /** 税额 getter 方法 */
  public UFDouble getNtax() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NTAX);
  }

  /** 税率 getter 方法 */
  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NTAXRATE);
  }

  /** 收货库存组织版本信息 getter 方法 */
  public String getPk_arrvstoorg() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ARRVSTOORG);
  }

  /** 收货库存组织 getter 方法 */
  public String getPk_arrvstoorg_v() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ARRVSTOORG_V);
  }

  /** 物料版本信息 getter 方法 */
  public String getPk_material() {
    return (String) this.getAttributeValue(OrderItemVO.PK_MATERIAL);
  }

  /** 采购订单明细 getter 方法 */
  public String getPk_order() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ORDER);
  }

  /** 采购订单明细 getter 方法 */
  public String getPk_order_b() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ORDER_B);
  }

  /** 财务组织 getter 方法 */
  public String getPk_psfinanceorg() {
    return (String) this.getAttributeValue(OrderItemVO.PK_PSFINANCEORG);
  }

  /** 财务组织版本信息 getter 方法 */
  public String getPk_psfinanceorg_v() {
    return (String) this.getAttributeValue(OrderItemVO.PK_PSFINANCEORG_V);
  }

  /** 收货地址 getter 方法 */
  public String getPk_receiveaddress() {
    return (String) this.getAttributeValue(OrderItemVO.PK_RECEIVEADDRESS);
  }

  /** 收货仓库 getter 方法 */
  public String getPk_recvstordoc() {
    return (String) this.getAttributeValue(OrderItemVO.PK_RECVSTORDOC);
  }

  /** 需求部门 getter 方法 */
  public String getPk_reqdept() {
    return (String) this.getAttributeValue(OrderItemVO.PK_REQDEPT);
  }

  /** 需求部门版本信息 getter 方法 */
  public String getPk_reqdept_v() {
    return (String) this.getAttributeValue(OrderItemVO.PK_REQDEPT_V);
  }

  /** ts getter 方法 */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(OrderItemVO.TS);
  }

  /** 批次号 getter 方法 */
  public String getVbatchcode() {
    return (String) this.getAttributeValue(OrderItemVO.VBATCHCODE);
  }

  /** 合同号 getter 方法 */
  public String getVcontractcode() {
    return (String) this.getAttributeValue(OrderItemVO.VCONTRACTCODE);
  }

  /** 自由辅助属性1 getter 方法 */
  public String getVfree1() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE1);
  }

  /** 自由辅助属性10 getter 方法 */
  public String getVfree10() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE10);
  }

  /** 自由辅助属性2 getter 方法 */
  public String getVfree2() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE2);
  }

  /** 自由辅助属性3 getter 方法 */
  public String getVfree3() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE3);
  }

  /** 自由辅助属性4 getter 方法 */
  public String getVfree4() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE4);
  }

  /** 自由辅助属性5 getter 方法 */
  public String getVfree5() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE5);
  }

  /** 自由辅助属性6 getter 方法 */
  public String getVfree6() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE6);
  }

  /** 自由辅助属性7 getter 方法 */
  public String getVfree7() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE7);
  }

  /** 自由辅助属性8 getter 方法 */
  public String getVfree8() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE8);
  }

  /** 自由辅助属性9 getter 方法 */
  public String getVfree9() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE9);
  }
}
