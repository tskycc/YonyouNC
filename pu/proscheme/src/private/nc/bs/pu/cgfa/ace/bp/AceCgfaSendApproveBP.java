package nc.bs.pu.cgfa.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pp.m28.entity.PriceAuditVO;
import nc.vo.pu.cgfa.AggCgfa;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据送审的BP
 */
public class AceCgfaSendApproveBP {
	/**
	 * 送审动作
	 * 
	 * @param vos
	 *            单据VO数组
	 * @param script
	 *            单据动作脚本对象
	 * @return 送审后的单据VO数组
	 */

	public AggCgfa[] sendApprove(AggCgfa[] clientBills, AggCgfa[] originBills) {
		for (AggCgfa clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue(
					"${vmObject.billstatus}", BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 数据持久化
		AggCgfa[] returnVos = new BillUpdate<AggCgfa>().update(clientBills,
				originBills);
		// 为了返回正确的数据做一个特殊处理 王梓懿 2018-06-11
		String[] ids = AggVOUtil.getPrimaryKeys(returnVos);
		AggCgfa[] newVos = new BillQuery<AggCgfa>(AggCgfa.class).query(ids);

		return newVos;
	}
}
