/**
 * $文件说明$
 *
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-9 上午08:48:36
 */
package nc.ui.pu.m21.action.orderclose.rowopen;

import java.awt.event.ActionEvent;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m21.IOrderClose;
import nc.ui.pu.m21.action.orderclose.AfterOrderCloseAction;
import nc.ui.pu.m21.service.OrderCloseManageModel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.pu.m21.entity.OrderCloseVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.enumeration.EnumCloseFlag;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillToServer;
import nc.vo.scmpub.res.SCMActionCode;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>开票行打开
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-4-9 上午08:48:36
 */
public class InvoiceRowOpenAction extends AfterOrderCloseAction {

  /**
   *
   */
  private static final long serialVersionUID = 1441862276471010302L;

  private OrderCloseManageModel model = null;

  public InvoiceRowOpenAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_INVOICEOPEN);
    // this.setBtnName("开票打开");
    // this.setCode("invoiceRowOpen");

  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    // 检查有没有选择数据行
    this.doBeforeAction();

    OrderVO[] vos = OrderCloseVO.getOrderVO(this.model.getSelectedCloseVO());

    ClientBillToServer<OrderVO> tool = new ClientBillToServer<OrderVO>();
    OrderVO[] lightVOs = tool.construct(vos, vos);

    // 执行远程调用,进行行关闭
    IOrderClose orderCloseService =
        NCLocator.getInstance().lookup(IOrderClose.class);
    OrderVO[] returnVos =
        orderCloseService.open(lightVOs,
            ((Integer) EnumCloseFlag.INVOICE_CLOSE.value()).intValue(), false);

    new ClientBillCombinServer<OrderVO>().combine(vos, returnVos);

    // 如果是整单关闭下进行,开票打开后对应单据的其它行最终关闭取消勾选,此时需要对其他行也更新
    Map<Integer, OrderCloseVO> mapAllClose = this.model.getAllCloseRow();

    this.model
        .directlyUpdate(mapAllClose.values().toArray(new OrderCloseVO[0]));

  }

  public OrderCloseManageModel getModel() {
    return this.model;
  }

  public void setModel(OrderCloseManageModel model) {
    this.model = model;
    this.model.addAppEventListener(this);
  }

  private void doBeforeAction() {
    if (ArrayUtils.isEmpty(this.model.getSelectedOperaDatas())) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0019")/*
                                                                   * @res
                                                                   * "请先选择要开票打开行"
                                                                   */);
    }
  }

  @Override
  protected boolean isActionEnable() {

    // 是否有选中的行
    OrderCloseVO[] orderVOs = this.model.getSelectedCloseVO();
    if (ArrayUtils.isEmpty(orderVOs)) {
      return false;
    }

    for (OrderCloseVO vo : orderVOs) {
      if (vo.getBinvoiceclose().booleanValue()) {
        return true;
      }
    }
    return false;
  }

}
