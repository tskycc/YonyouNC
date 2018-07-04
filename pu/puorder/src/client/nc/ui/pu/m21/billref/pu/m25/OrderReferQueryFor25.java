/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-4 上午11:32:14
 */
package nc.ui.pu.m21.billref.pu.m25;

import java.awt.Container;

import nc.itf.org.IOrgConst;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.scmpub.reference.uap.setting.defaultdata.DefaultDataSettingAccessor;
import nc.ui.pu.m21.view.OrderQueryDLGInitializer;
import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QMarPuClassFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QPaytermFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.constant.PUQueryConst;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单提供给采购发票的查询服务类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-5-4 上午11:32:14
 */
public class OrderReferQueryFor25 extends DefaultBillReferQuery {

  /**
   * @param c
   * @param info
   */
  public OrderReferQueryFor25(Container c, TemplateInfo info) {
    super(c, info);
  }

  public String getDefaultPk_OrgValue() {
    String pk_org;
    try {
      pk_org = DefaultDataSettingAccessor.getDefaultOrgUnit();
      if (StringUtil.isEmptyWithTrim(pk_org)) {
        return null;
      }
      if (OrgUnitPubService.isTypeOf(pk_org, IOrgConst.FINANCEORGTYPE)) {
        return pk_org;
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  private void filterOrgPower(QueryConditionDLGDelegator dlgDelegator) {
    // 主组织权限
    dlgDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      OrderQueryDLGInitializer.PK_ORDER_B_PK_PSFINANCEORG
    });
  }

  private void setFilter(QueryConditionDLGDelegator dlgDelegator) {
    // 采购部门，如果查询一个采购组织的数据，则可参照该组织所属的公司下的部门档案；否则可参照集团范围的部门档案。
    QDeptFilter deptFilter =
        QDeptFilter.createQDeptFilterOfPU(dlgDelegator, OrderHeaderVO.PK_DEPT);
    deptFilter.setbUsedflag(UFBoolean.TRUE);
    deptFilter.addEditorListener();

    // 采购员，如果查询一个采购组织的数据，则可参照该组织所属的公司下的人员档案；否则可参照集团范围的人员档案。
    QPsndocFilter.createQPsndocFilterOfPU(dlgDelegator,
        OrderHeaderVO.CEMPLOYEEID).addEditorListener();

    // 订单交易类型，参照采购订单交易类型录入
    new QTransTypeFilter(dlgDelegator, POBillType.Order.getCode()).filter();

    // 供应商编码，参照库存组织可见的供应商档案录入
    QSupplierFilter qSupplierFilter =
        new QSupplierFilter(dlgDelegator, PUQueryConst.SUPPLIER_CODE);
    qSupplierFilter.setPk_orgCode(OrderItemVO.PK_ORDER_B + PUQueryConst.DOT
        + OrderItemVO.PK_PSFINANCEORG);
    qSupplierFilter.addEditorListener();

    // 供应商名称，参照库存组织可见的供应商档案录入
    QSupplierFilter qSupplierNameFilter =
        new QSupplierFilter(dlgDelegator, PUQueryConst.SUPPLIER_NAME);
    qSupplierNameFilter.setPk_orgCode(OrderItemVO.PK_ORDER_B + PUQueryConst.DOT
        + OrderItemVO.PK_PSFINANCEORG);
    qSupplierNameFilter.addEditorListener();

    // 物料基本分类编码，参照库存组织可见的物料基本分类档案录入
    QMarbasclassFilter qMarbasclassFilter =
        new QMarbasclassFilter(dlgDelegator, OrderItemVO.PK_ORDER_B
            + PUQueryConst.MARBASCLASS_CODE);
    qMarbasclassFilter.setPk_orgCode(OrderItemVO.PK_ORDER_B + PUQueryConst.DOT
        + OrderItemVO.PK_PSFINANCEORG);
    qMarbasclassFilter.addEditorListener();

    // 物料采购分类编码，参照库存组织可见的物料采购分类档案录入
    QMarPuClassFilter qMarPuClassFilter =
        new QMarPuClassFilter(dlgDelegator, OrderItemVO.PK_ORDER_B
            + PUQueryConst.MARPUCLASS_CODE);
    qMarPuClassFilter.setPk_orgCode(OrderItemVO.PK_ORDER_B + PUQueryConst.DOT
        + OrderItemVO.PK_PSFINANCEORG);
    qMarPuClassFilter.addEditorListener();

    // 物料编码，参照库存组织可见的物料档案录入，发票不过滤费用和折扣物料
    new QMarterialFilter(dlgDelegator, OrderItemVO.PK_ORDER_B
        + PUQueryConst.DOT + OrderItemVO.PK_PSFINANCEORG,
        OrderItemVO.PK_ORDER_B + PUQueryConst.MATERIAL_CODE)
        .addEditorListener();

    // 物料名称，参照库存组织可见的物料档案录入，发票不过滤费用和折扣物料
    new QMarterialFilter(dlgDelegator, OrderItemVO.PK_ORDER_B
        + PUQueryConst.DOT + OrderItemVO.PK_PSFINANCEORG,
        OrderItemVO.PK_ORDER_B + PUQueryConst.MATERIAL_NAME)
        .addEditorListener();

    new QMarterialFilter(dlgDelegator, OrderItemVO.PK_ORDER_B
        + PUQueryConst.DOT + OrderItemVO.PK_PSFINANCEORG,
        OrderItemVO.PK_ORDER_B + PUQueryConst.SRCMATERIAL_CODE)
        .addEditorListener();

    // 物料名称，参照库存组织可见的物料档案录入，发票不过滤费用和折扣物料
    new QMarterialFilter(dlgDelegator, OrderItemVO.PK_ORDER_B
        + PUQueryConst.DOT + OrderItemVO.PK_PSFINANCEORG,
        OrderItemVO.PK_ORDER_B + PUQueryConst.SRCMATERIAL_NAME)
        .addEditorListener();

    // 开票供应商
    QSupplierFilter invsupplier =
        new QSupplierFilter(dlgDelegator, OrderHeaderVO.PK_INVCSUPLLIER);
    invsupplier.setPk_orgCode(OrderItemVO.PK_ORDER_B + PUQueryConst.DOT
        + OrderItemVO.PK_PSFINANCEORG);
    invsupplier.addEditorListener();

    // 收货客户
    QCustomerFilter recvcustomer =
        new QCustomerFilter(dlgDelegator, OrderHeaderVO.PK_RECVCUSTOMER);
    recvcustomer.setPk_orgCode(OrderItemVO.PK_ORDER_B + PUQueryConst.DOT
        + OrderItemVO.PK_PSFINANCEORG);
    recvcustomer.addEditorListener();

    // 付款协议
    new QPaytermFilter(dlgDelegator, OrderHeaderVO.PK_PAYTERM)
        .addEditorListener();

    // 需求仓库
    new QWareHouseFilter(dlgDelegator, OrderItemVO.PK_ORDER_B
        + PUQueryConst.DOT + OrderItemVO.PK_REQSTOORG, OrderItemVO.PK_ORDER_B
        + PUQueryConst.DOT + OrderItemVO.PK_REQSTORDOC).addEditorListener();

    // 收货仓库
    QWareHouseFilter wareHouseFilter =
        new QWareHouseFilter(dlgDelegator, OrderItemVO.PK_ORDER_B
            + PUQueryConst.DOT + OrderItemVO.PK_ARRVSTOORG,
            OrderItemVO.PK_ORDER_B + PUQueryConst.DOT
                + OrderItemVO.PK_RECVSTORDOC);
    wareHouseFilter.setbGubflag(UFBoolean.FALSE);
    wareHouseFilter.addEditorListener();

    // 项目
    new QProjectFilter(dlgDelegator, OrderItemVO.PK_ORDER_B + PUQueryConst.DOT
        + OrderItemVO.PK_PSFINANCEORG, OrderItemVO.PK_ORDER_B
        + PUQueryConst.DOT + OrderItemVO.CPROJECTID).addEditorListener();
    // 按主组织过滤客户
    new QCustomerFilter(dlgDelegator, OrderItemVO.PK_ORDER_B + PUQueryConst.DOT
        + PuAttrNameEnum.casscustid.name()).addEditorListener();
  }

  @Override
  protected void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {
    // 组织权限过滤
    this.filterOrgPower(dlgDelegator);
    this.setFilter(dlgDelegator);

    dlgDelegator.addRedundancyInfo(OrderHeaderVO.PK_ORG, "pk_order_b."
        + OrderItemVO.PK_ORG);
    dlgDelegator.addRedundancyInfo(OrderHeaderVO.DBILLDATE, "pk_order_b."
        + OrderItemVO.DBILLDATE);
    dlgDelegator.addRedundancyInfo(OrderHeaderVO.PK_SUPPLIER, "pk_order_b."
        + OrderItemVO.PK_SUPPLIER);

    dlgDelegator.setDefaultValue("pk_order_b.pk_psfinanceorg",
        this.getDefaultPk_OrgValue());
    dlgDelegator.setDefaultValue("pk_org", null);
    dlgDelegator.setPowerEnable(true);
    dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }
}
