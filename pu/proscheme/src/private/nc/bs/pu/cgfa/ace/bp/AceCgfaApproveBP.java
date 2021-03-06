package nc.bs.pu.cgfa.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.pu.cgfa.AggCgfa;
import nc.vo.pu.pub.util.AggVOUtil;

/**
 * 标准单据审核的BP
 */
public class AceCgfaApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggCgfa[] approve(AggCgfa[] clientBills, AggCgfa[] originBills) {
		for (AggCgfa clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggCgfa> update = new BillUpdate<AggCgfa>();
		AggCgfa[] returnVos = update.update(clientBills, originBills);

		String[] ids = AggVOUtil.getPrimaryKeys(returnVos);
		AggCgfa[] newVos = new BillQuery<AggCgfa>(AggCgfa.class).query(ids);
		return newVos;
	}

}
