package nc.ui.pu.cgfa.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pu.QueryForLineCloseImpl;
import nc.itf.pu.IQueryForLineClose;
import nc.itf.pubapp.pub.smart.IBillQueryService;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.pu.cgfa.Cgfa;
import nc.vo.pu.cgfa.Cgfab;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

/**
 * @author wangzym
 * @version 2017��5��8�� ����12:10:47
 */
public class CancelLineCloseAction extends NCAction {

	private ShowUpableBillForm editor;
	private BillManageModel model;

	public CancelLineCloseAction() {
		// TODO �Զ����ɵĹ��캯�����
		this.setBtnName("ȡ���йر�");
		this.setCode("qxhgb");
	}

	/**
	 * @return editor
	 */
	public ShowUpableBillForm getEditor() {
		return editor;
	}

	/**
	 * @param editor
	 *            Ҫ���õ� editor
	 */
	public void setEditor(ShowUpableBillForm editor) {
		this.editor = editor;
	}

	/**
	 * @return model
	 */
	public BillManageModel getModel() {
		return model;
	}

	/**
	 * @param model
	 *            Ҫ���õ� model
	 */
	public void setModel(BillManageModel model) {
		this.model = model;
		model.addAppEventListener(this);
	}

	/*
	 * ���� Javadoc��
	 * 
	 * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
	 */
	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO �Զ����ɵķ������

		int[] rows = editor.getBillCardPanel().getBillTable().getSelectedRows();

		List<Cgfab> bvo = new ArrayList<Cgfab>();
		for (int i = 0; i < rows.length; i++) {
			int j = rows[i];
			Cgfab cgfab = (Cgfab) editor.getBillCardPanel().getBillModel()
					.getBodyValueRowVO(j, Cgfab.class.getName());
			bvo.add(cgfab);
		}

		Cgfab[] bvos = bvo.toArray(new Cgfab[bvo.size()]);
		HashMap<Integer, String> bpks = new HashMap<Integer, String>();
		List<String> bsrcpks = new ArrayList<String>();
		List<UFDouble> cgfaNum = new ArrayList<UFDouble>();
		// Integer[] rows = getModel().getSelectedOperaRows();
		// int[] row=editor.getBillCardPanel().getBillTable().getSelectedRows()
		int sum = 0;
		for (int i = 0; i < bvos.length; i++) {
			Cgfab cgfab = bvos[i];
			if (!"".equals(cgfab.getPrimaryKey())) {
				sum += 1;
			}

			bpks.put(rows[i], cgfab.getPrimaryKey());
			bsrcpks.add((String) cgfab.getAttributeValue("csrcid"));
			cgfaNum.add((UFDouble) cgfab.getAttributeValue("cgfanum"));
		}

		// �µ����йرղ��飬��ʡ��Դ
		if (sum == 0) {
			cancelLineClose();
			reWriteDB(bsrcpks, cgfaNum);

		} else {
			// ��֤�����Ƿ��Ѿ�����������,����У��򷵻ؾ����к�
			// ��������֤�����Ƿ������������������û�в��ض�β��ұ��壬��ʱ����д

			int[] index = validataHaveForword(bpks);
			if (index.length == 0) {
				cancelLineClose();
				reWriteDB(bsrcpks, cgfaNum);
			} else {
				// ������ʾ���ڼ����Ѿ����������ݲ������йر�
				String row = index.toString();
				String errorMsg = "�� " + row + "���Ѿ����������ݣ��������йر�";
				MessageDialog.showErrorDlg(editor, "ѡȡ���ݴ���", errorMsg);
			}

		}
		refresh(this.getModel().getSelectedData());
		this.model.fireShowEditorEvent();

	}
	
	/**
	 * @param obj
	 * @throws BusinessException
	 */
	private void refresh(Object obj) throws BusinessException {
		// TODO �Զ����ɵķ������
		AbstractBill oldVO = (AbstractBill) obj;
		String pk = oldVO.getParentVO().getPrimaryKey();
		IBillQueryService billQuery = NCLocator.getInstance().lookup(
				IBillQueryService.class);
		AggregatedValueObject newVO = billQuery.querySingleBillByPk(
				oldVO.getClass(), pk);

		this.model.directlyUpdate(newVO);

	}

	
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * @param bsrcpks
	 * @param cgfaNum
	 */
	private void reWriteDB(List<String> bsrcpks, List<UFDouble> cgfaNum) {
		// TODO �Զ����ɵķ������
		// ��д���ݿ�
		IQueryForLineClose close = NCLocator.getInstance().lookup(
				IQueryForLineClose.class);
		close.reWritePrayBill(bsrcpks.toArray(new String[bsrcpks.size()]),
				cgfaNum.toArray(new UFDouble[cgfaNum.size()]), "qxhgb");
	}

	/**
	 * @param bpks
	 * 
	 */
	private int[] validataHaveForword(HashMap<Integer, String> bpks) {
		// TODO �Զ����ɵķ������
		IQueryForLineClose query = NCLocator.getInstance().lookup(
				IQueryForLineClose.class);
		int[] rows = query.hasForword(bpks);
		return rows;
	}

	/**
	 * 
	 */
	private void cancelLineClose() {
		// TODO �Զ����ɵķ������
		int[] rows = editor.getBillCardPanel().getBillTable().getSelectedRows();
		List<String> pks = new ArrayList<String>();
		for (Integer integer : rows) {
			editor.getBillCardPanel().setBodyValueAt(UFBoolean.FALSE,
					integer.intValue(), "whetherlineclose");
			String pk_cgfab = (String) editor.getBillCardPanel()
					.getBodyValueAt(integer.intValue(), "pk_equipment_sub");
			pks.add(pk_cgfab);
		}
		IQueryForLineClose update = NCLocator.getInstance().lookup(
				IQueryForLineClose.class);
		update.updateForSelf(pks.toArray(new String[pks.size()]), "qxhgb");

	}

	/*
	 * ���� Javadoc��
	 * 
	 * @see nc.ui.uif2.NCAction#isActionEnable()
	 */
	@Override
	protected boolean isActionEnable() {
		// TODO �Զ����ɵķ������
		int[] rows = editor.getBillCardPanel().getBillTable().getSelectedRows();
		if (rows.length == 0) {
			return false;
		}

		List<Cgfab> bvo = new ArrayList<Cgfab>();
		for (int i = 0; i < rows.length; i++) {
			int j = rows[i];
			Cgfab cgfab = (Cgfab) editor.getBillCardPanel().getBillModel()
					.getBodyValueRowVO(j, Cgfab.class.getName());
			bvo.add(cgfab);
		}

		Cgfab[] bvos = bvo.toArray(new Cgfab[bvo.size()]);
		for (Cgfab cgfab : bvos) {
			UFBoolean lineClose = (UFBoolean) cgfab
					.getAttributeValue("whetherlineclose");
			if (UFBoolean.FALSE.equals(lineClose)) {
				return false;
			}
		}
		return true;
	}

}