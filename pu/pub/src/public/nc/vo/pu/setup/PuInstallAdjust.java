package nc.vo.pu.setup;

import nc.bs.ecpubapp.accountmanage.NewInstallAjust4PU;
import nc.bs.sm.accountmanage.INewInstallAdjust;

/**
 * 采购安装处理
 * 
 * @since 6.0
 * @version 2011-10-28 上午10:23:17
 * @author wuxla
 */

public class PuInstallAdjust implements INewInstallAdjust {

  @Override
  public void adjust(String newVersion) throws Exception {
    /**
     * 在安装同时安装了EC和SCM产品时，需要
     * 1：将EC产品的采购订单、采购协议合同、采购订单查询、合同协议报价查询、采购协议合同查询五个功能节点置为不启用
     * 2：将SCM的采购订单中的发布、发布人、发布时间、响应状态、响应人、响应时间、拒绝原因7个字段置为显示不可编辑状态
     * 3：将SCM的到货计划单据中联系人、联系方式、运输方式、车牌号、备注五个字段置为显示不可编辑状态。
     */
    NewInstallAjust4PU ecinstall = new NewInstallAjust4PU();
    ecinstall.adjust(newVersion);
  }

}
