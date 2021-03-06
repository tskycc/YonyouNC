/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-26 上午11:25:49
 */
package nc.ui.pu.m21.action;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.KeyStroke;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.uap.pf.busiflow.PfButtonClickContext;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.editor.IBillCardPanelEditor;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>参照协同销售订单
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-26 上午11:25:49
 */
public class AddFromSaleOrderCoopAction extends AbstractReferenceAction {
  private static final long serialVersionUID = 2153952390442217584L;

  private IBillCardPanelEditor editor;

  private AbstractAppModel model;

  public AddFromSaleOrderCoopAction() {
    super();
    this.setCode("RefCoopTo30");
    this.putValue(Action.ACCELERATOR_KEY,
        KeyStroke.getKeyStroke(KeyEvent.VK_E, Event.CTRL_MASK | Event.ALT_MASK));
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (!SysInitGroupQuery.isSOEnabled()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0030")/*
                                                                   * @res
                                                                   * "销售模块未启用"
                                                                   */);
      return;
    }
    this.editor.getBillCardPanel().getBodyTabbedPane().setSelectedIndex(0);
    PfUtilClient.childButtonClickedNew(this.createPfButtonClickContext());
    if (PfUtilClient.isCloseOK()) {
      OrderVO[] destVOs = (OrderVO[]) PfUtilClient.getRetVos();

      // 显示到转单界面上
      this.getTransferViewProcessor().processBillTransfer(destVOs);

      // 可编辑性
      this.setEditable();
    }
  }

  /**
   * @return editor
   */
  public IBillCardPanelEditor getEditor() {
    return this.editor;
  }

  /**
   * @return model
   */
  public AbstractAppModel getModel() {
    return this.model;
  }

  @Override
  public void putValue(String key, Object newValue) {
    if (Action.NAME.equals(key) || Action.SHORT_DESCRIPTION.equals(key)) {
      super.putValue(key, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("common", "14004000-0003")/*
                                                 * 协同销售订单
                                                 */);
    }
    else {
      super.putValue(key, newValue);
    }
  }

  /**
   * @param editor
   *          要设置的 editor
   */
  public void setEditor(IBillCardPanelEditor editor) {
    this.editor = editor;
  }

  /**
   * @param model
   *          要设置的 model
   */
  public void setModel(AbstractAppModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  private PfButtonClickContext createPfButtonClickContext() {
    PfButtonClickContext context = new PfButtonClickContext();
    context.setParent(this.getModel().getContext().getEntranceUI());
    context.setSrcBillType(this.getSourceBillType());
    context.setPk_group(this.getModel().getContext().getPk_group());
    context.setUserId(this.getModel().getContext().getPk_loginUser());
    // 如果该节点是由交易类型发布的，那么这个参数应该传交易类型，否则传单据类型
    context.setCurrBilltype(PoTransTypeVO.M21_COOP);
    context.setUserObj(null);
    context.setSrcBillId(null);
    context.setBusiTypes(this.getBusitypes());
    // 上面的参数在原来调用的方法中都有涉及，只不过封成了一个整结构，下面两个参数是新加的参数
    // 上游的交易类型集合
    context.setTransTypes(this.getTranstypes());
    // 标志在交换根据目的交易类型分组时，查找目的交易类型的依据，有三个可设置值：1（根据接口定义）、
    // 2（根据流程配置）、-1（不根据交易类型分组）
    context.setClassifyMode(PfButtonClickContext.ClassifyByItfdef);
    return context;
  }

  /**
   * 方法功能描述：可编辑性
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-9-28 下午12:06:39
   */
  private void setEditable() {
    BillCardPanel panel = this.editor.getBillCardPanel();
    panel.getHeadItem(OrderHeaderVO.PK_SUPPLIER).setEnabled(false);
    for (int i = 0; i < panel.getRowCount(); ++i) {
      // panel.setCellEditable(i, OrderItemVO.PK_MATERIAL, false);
      panel.setCellEditable(i, OrderItemVO.PK_PSFINANCEORG_V, false);
      panel.setCellEditable(i, OrderItemVO.PK_PSFINANCEORG, false);
    }
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    return this.model.getUiState() == UIState.NOT_EDIT;
  }

}
