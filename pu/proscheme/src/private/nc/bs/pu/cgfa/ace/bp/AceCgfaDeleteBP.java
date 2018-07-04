package nc.bs.pu.cgfa.ace.bp;

import nc.bs.pu.cgfa.ace.rule.RewriteForPrayBillRule;
import nc.bs.pu.cgfa.plugin.bpplugin.CgfaPluginPoint;
import nc.vo.pu.cgfa.AggCgfa;
import nc.impl.pu.delReWrite.DelRewriteForPrayBillRule;
import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceCgfaDeleteBP {

	public void delete(AggCgfa[] bills) {

		DeleteBPTemplate<AggCgfa> bp = new DeleteBPTemplate<AggCgfa>(
				CgfaPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggCgfa> processer) {
		// TODO 前规则
		IRule<AggCgfa> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggCgfa> processer) {
		// TODO 后规则

		//回写请购单规则
		processer.addAfterFinalRule(new DelRewriteForPrayBillRule());
	}
}
