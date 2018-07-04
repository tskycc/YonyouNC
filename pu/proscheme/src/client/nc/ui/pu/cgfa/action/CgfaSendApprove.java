/**   
 * Copyright  2018 Yonyou. All rights reserved.
 * @Description: TODO
 * @author: wangzy   
 * @date: 2018��3��22�� ����3:33:51 
 * @version: V6.5   
 */
package nc.ui.pu.cgfa.action;

import java.awt.event.ActionEvent;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.pubapp.pub.smart.IBillQueryService;
import nc.ui.pubapp.uif2app.actions.pflow.CommitScriptAction;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.bd.meta.IBDObject;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

/**
 * @Description: �����ύ������˺���ʾ�������blobֵ���ԣ����Ե�ˢ�·���
 *               ��ʵ����Ҫ�ĵײ�BillUpdate��ʵ�֣��漰��������̫�࣬�Ȳ����ˣ�
 * @author: wangzy
 * @date: 2018��3��22�� ����3:33:51
 */
public class CgfaSendApprove extends CommitScriptAction {

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO �Զ����ɵķ������
		super.doAction(e);
		// ˢ��һ�½���
		//�ں�̨������������ǰ̨ ����ܲ 2018-06-11
		//reFreshDate(this.getModel());
	}

	/**
	 * @Title: reFreshDate
	 * @Description: �ֲ�ƽ̨����
	 * дһ���������õķ����������ȡ
	 * @throws Exception
	 * @return: void
	 */
	public static void reFreshDate(BillManageModel bmModel) throws Exception {
		Object data = bmModel.getSelectedData();
		if (data == null) {
			return;
		}
		String pk = "";
		int i = 0;
		Class<AbstractBill> clazz = null;
		IBDObject target = bmModel.getBusinessObjectAdapterFactory()
				.createBDObject(data);
		pk = (String) target.getId();
		clazz = (Class<AbstractBill>) data.getClass();
		if (clazz == null) {
			return;
		}

		// ע�⣺�����д��ֻ����ʱ��д����Ϊ����ʱ���CQ���⣬��δ����Ժ�϶�Ҫ�޸ĵģ��ڵȴ�����Ľӿ�
		// �����Ӱ��Ч�ʣ������ܶ�������
		IBillQueryService billQuery = NCLocator.getInstance().lookup(
				IBillQueryService.class);
		AbstractBill bills = billQuery.querySingleBillByPk(clazz, pk);

		if (bills == null) {
			return;
		}
		bmModel.directlyUpdate(bills);
	}

}