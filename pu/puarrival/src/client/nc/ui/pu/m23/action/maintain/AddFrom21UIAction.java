package nc.ui.pu.m23.action.maintain;

import java.awt.event.ActionEvent;
import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.uap.pf.busiflow.PfButtonClickContext;
import nc.ui.pu.m23.editor.card.utils.BackReasonEditUtil;
import nc.ui.pu.pub.util.PuVDefFilterUtil;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
import nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeFuncUtils;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.rule.transfer.CalcValidDay;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������ ����--�ɹ����� ��ť������Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-12 ����02:15:12
 */
public class AddFrom21UIAction extends AbstractReferenceAction {

  private static final long serialVersionUID = 6197534415524885685L;

  private ShowUpableBillForm editor = null;

  private AbstractAppModel model = null;

  public AddFrom21UIAction() {
    super();
    this.setSourceBillName(POBillType.Order.getName());
    this.setSourceBillType(POBillType.Order.getCode());
    editor.getBillCardPanel().getBillModel().loadLoadRelationItemValue();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    PfUtilClient.childButtonClickedNew(this.createPfButtonClickContext());

    if (PfUtilClient.isCloseOK()) {
      AggregatedValueObject[] retObjArray = PfUtilClient.getRetVos();
      if (ArrayUtils.isEmpty(retObjArray)) {
        return;
      }

      ArriveVO[] retVOArray = (ArriveVO[]) retObjArray;

      // ת����������
      this.getTransferViewProcessor().processBillTransfer(retVOArray);

      BillCardPanel card = this.editor.getBillCardPanel();
      // �����˻�����
      new BackReasonEditUtil(card).setEnabled();

      // �����༭�¼�
      // String stockOrg = retVOArray[0].getHVO().getPk_org();
      // this.model.fireEvent(new OrgChangedEvent(null, stockOrg));
      new PuVDefFilterUtil()
          .setOrgForDefRef(card, this.getModel().getContext());

      // ���䱣��������
      this.setValiday(card, retVOArray);
    }
    this.setEditable();
  }

  public ShowUpableBillForm getEditor() {
    return this.editor;
  }

  public AbstractAppModel getModel() {
    return this.model;
  }

  public void setEditor(ShowUpableBillForm editor) {
    this.editor = editor;
  }

  public void setModel(AbstractAppModel model) {
    this.model = model;
  }

  private PfButtonClickContext createPfButtonClickContext() {
    PfButtonClickContext context = new PfButtonClickContext();
    context.setParent(this.getModel().getContext().getEntranceUI());
    context.setSrcBillType(this.getSourceBillType());
    context.setPk_group(this.getModel().getContext().getPk_group());
    context.setUserId(this.getModel().getContext().getPk_loginUser());
    // ����ýڵ����ɽ������ͷ����ģ���ô�������Ӧ�ô��������ͣ����򴫵�������
    String vtrantype =
        TrantypeFuncUtils.getTrantype(this.getModel().getContext());
    if (StringUtil.isEmptyWithTrim(vtrantype)) {
      context.setCurrBilltype(POBillType.Arrive.getCode());
    }
    else {
      context.setCurrBilltype(vtrantype);
    }
    context.setUserObj(null);
    context.setSrcBillId(null);
    context.setBusiTypes(this.getBusitypes());
    // ����Ĳ�����ԭ�����õķ����ж����漰��ֻ���������һ�����ṹ�����������������¼ӵĲ���
    // ���εĽ������ͼ���
    context.setTransTypes(this.getTranstypes());
    // ��־�ڽ�������Ŀ�Ľ������ͷ���ʱ������Ŀ�Ľ������͵����ݣ�������������ֵ��1�����ݽӿڶ��壩��
    // 2�������������ã���-1�������ݽ������ͷ��飩
    context.setClassifyMode(PfButtonClickContext.ClassifyByBusiflow);
    return context;
  }

  /**
   * ���ñ༭��
   */
  private void setEditable() {
    BillCardPanel panel = this.editor.getBillCardPanel();
    for (int i = 0; i < panel.getRowCount(); ++i) {
      // zhangshqb 2015-4-28 636���������ջ����������Լ������������ģ������ɱ༭
      panel.setCellEditable(i, ArriveItemVO.PK_APLIABCENTER, false);
      panel.setCellEditable(i, ArriveItemVO.PK_APLIABCENTER_V, false);
      panel.setCellEditable(i, ArriveItemVO.PK_ARRLIABCENTER, false);
      panel.setCellEditable(i, ArriveItemVO.PK_ARRLIABCENTER_V, false);
    }
  }

  private void setValiday(BillCardPanel card, ArriveVO[] vos) {
    CalcValidDay util = new CalcValidDay();
    String[] fields =
        new String[] {
          MaterialStockVO.QUALITYMANFLAG, MaterialStockVO.QUALITYUNIT,
          MaterialStockVO.QUALITYNUM
        };
    // �õ�������������
    String[] mrls =
        (String[]) AggVOUtil.getDistinctItemFieldArray(vos,
            ArriveItemVO.PK_MATERIAL, String.class);
    String org = vos[0].getHVO().getPk_org();
    Map<String, MaterialStockVO> map =
        MaterialPubService.queryMaterialStockInfo(mrls, org, fields);
    for (int i = 0; i < card.getRowCount(); i++) {
      String mrl = (String) card.getBodyValueAt(i, ArriveItemVO.PK_MATERIAL);
      if (map != null && map.get(mrl) != null) {
        Integer validDay = util.calcValidDay(map.get(mrl));
        card.setBodyValueAt(validDay, i, ArriveItemVO.IVALIDDAY);
      }
    }
  }
}