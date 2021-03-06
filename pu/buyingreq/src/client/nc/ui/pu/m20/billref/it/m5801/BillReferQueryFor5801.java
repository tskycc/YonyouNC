package nc.ui.pu.m20.billref.it.m5801;

import java.awt.Container;

import nc.ui.pu.m20.query.PraybillQueryDLGInitializer;
import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QMarPuClassFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.res.billtype.ITBillType;

/**
 * 请购单提供给进口合同的查询类
 * TODO 将改类注册到vo对照上
 * 
 * @since 6.3.1
 * @version 2013-7-3上午11:18:31
 * @author fanly3
 */
public class BillReferQueryFor5801 extends DefaultBillReferQuery {

  public BillReferQueryFor5801(Container c, TemplateInfo info) {
    super(c, info);
  }

  // public String getDefaultPk_OrgValue() {
  // try {
  // String pk_org = DefaultDataSettingAccessor.getDefaultOrgUnit();
  // if (StringUtil.isEmptyWithTrim(pk_org)) {
  // return null;
  // }
  // if (OrgUnitPubService.isTypeOf(pk_org, IOrgConst.PURCHASEORGTYPE)) {
  // return pk_org;
  // }
  // }
  // catch (Exception e) {
  // ExceptionUtils.wrappException(e);
  // }
  // return null;
  // }

  private void filterOrgPower(QueryConditionDLGDelegator dlgDelegator) {
    // 主组织权限
    dlgDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      PraybillQueryDLGInitializer.PK_PRAYBILL_B_PK_PURCHASEORG
    });
    // 档案使用权限
    dlgDelegator.setPowerEnable(true);
  }

  private void setFilter(QueryConditionDLGDelegator dlgDelegator) {
    // 订单交易类型，参照采购订单交易类型录入
    new QTransTypeFilter(dlgDelegator, "pk_praybill_b.cordertrantypecode",
        ITBillType.Contract.getCode()).filter();

    // 计划部门，参照集团范围的部门档案录入。
    QDeptFilter planDeptFilter =
        QDeptFilter.createDeptFilterOfIC(dlgDelegator,
            PraybillHeaderVO.PK_PLANDEPT);
    planDeptFilter.setPk_orgCode(PraybillHeaderVO.PK_ORG);
    planDeptFilter.addEditorListener();

    // 计划员，参照集团范围的人员档案录入。
    QPsndocFilter planPsndocFilter =
        QPsndocFilter.createQPsndocFilterOfIC(dlgDelegator,
            PraybillHeaderVO.PK_PLANPSN);
    planPsndocFilter.setPk_orgCode(PraybillHeaderVO.PK_ORG);
    planPsndocFilter.addEditorListener();

    // 物料基本分类编码，参照采购组织的物料基本分类
    QMarbasclassFilter qMarbasclassFilter =
        new QMarbasclassFilter(dlgDelegator,
            "pk_praybill_b.pk_material.pk_marbasclass.code");
    qMarbasclassFilter.setPk_orgCode("pk_praybill_b.pk_purchaseorg");
    qMarbasclassFilter.addEditorListener();

    QMarPuClassFilter qMarPuClassFilter =
        new QMarPuClassFilter(dlgDelegator,
            "pk_praybill_b.pk_material.materialstock.pk_marpuclass.code");
    qMarPuClassFilter.setPk_orgCode(PraybillHeaderVO.PK_ORG);
    qMarPuClassFilter.addEditorListener();

    // 物料编码，参照采购组织可见的物料档案录入
    new QMarterialFilter(dlgDelegator, "pk_praybill_b.pk_purchaseorg",
        "pk_praybill_b.pk_srcmaterial.code").addEditorListener();
    new QMarterialFilter(dlgDelegator, "pk_praybill_b.pk_purchaseorg",
        "pk_praybill_b.pk_srcmaterial.name").addEditorListener();

    // 供应商编码，参照采购组织可见的供应商档案录入
    QSupplierFilter qSupplierFilter =
        new QSupplierFilter(dlgDelegator,
            "pk_praybill_b.pk_suggestsupplier.code");
    qSupplierFilter.setPk_orgCode("pk_praybill_b.pk_purchaseorg");
    qSupplierFilter.addEditorListener();

    // 供应商名称，参照采购组织可见的供应商档案录入
    QSupplierFilter qSupplierNameFilter =
        new QSupplierFilter(dlgDelegator,
            "pk_praybill_b.pk_suggestsupplier.name");
    qSupplierNameFilter.setPk_orgCode("pk_praybill_b.pk_purchaseorg");
    qSupplierNameFilter.addEditorListener();

    // 按采购组织过滤采购员
    QPsndocFilter psndocFilter =
        QPsndocFilter.createQPsndocFilterOfPU(dlgDelegator,
            "pk_praybill_b.pk_employee");
    psndocFilter.setPk_orgCode("pk_praybill_b.pk_purchaseorg");
    psndocFilter.addEditorListener();

    // 项目
    new QProjectFilter(dlgDelegator, "pk_praybill_b.pk_purchaseorg",
        "pk_praybill_b.cprojectid").addEditorListener();
    // 按主组织过滤客户
    new QCustomerFilter(dlgDelegator, "pk_praybill_b.casscustid")
        .addEditorListener();
  }

  @Override
  protected void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {
    // 组织权限过滤
    this.filterOrgPower(dlgDelegator);
    this.setFilter(dlgDelegator);
    dlgDelegator.setDefaultValue(PraybillHeaderVO.PK_ORG, null);
    // dlgDelegator.setDefaultValue("pk_praybill_b.pk_purchaseorg",
    // this.getDefaultPk_OrgValue());
    dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }
}
