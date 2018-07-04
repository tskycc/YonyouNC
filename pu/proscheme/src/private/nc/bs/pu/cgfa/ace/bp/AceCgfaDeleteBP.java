package nc.bs.pu.cgfa.ace.bp;

import nc.bs.pu.cgfa.ace.rule.RewriteForPrayBillRule;
import nc.bs.pu.cgfa.plugin.bpplugin.CgfaPluginPoint;
import nc.vo.pu.cgfa.AggCgfa;
import nc.impl.pu.delReWrite.DelRewriteForPrayBillRule;
import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceCgfaDeleteBP {

	public void delete(AggCgfa[] bills) {

		DeleteBPTemplate<AggCgfa> bp = new DeleteBPTemplate<AggCgfa>(
				CgfaPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggCgfa> processer) {
		// TODO ǰ����
		IRule<AggCgfa> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggCgfa> processer) {
		// TODO �����

		//��д�빺������
		processer.addAfterFinalRule(new DelRewriteForPrayBillRule());
	}
}