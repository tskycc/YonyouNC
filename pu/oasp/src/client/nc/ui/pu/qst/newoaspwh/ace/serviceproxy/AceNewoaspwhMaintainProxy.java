package nc.ui.pu.qst.newoaspwh.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.pubapp.uif2app.actions.IDataOperationService;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.uif2.components.pagination.IPaginationQueryService;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pu.qst.newoaspwh.AggNewoaspwhaHeadVO;
import nc.itf.pu.INewoaspwhMaintain;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceNewoaspwhMaintainProxy implements IDataOperationService,
		IQueryService ,ISingleBillService<AggNewoaspwhaHeadVO>{
	@Override
	public IBill[] insert(IBill[] value) throws BusinessException {
		INewoaspwhMaintain operator = NCLocator.getInstance().lookup(
				INewoaspwhMaintain.class);
		AggNewoaspwhaHeadVO[] vos = operator.insert((AggNewoaspwhaHeadVO[]) value);
		return vos;
	}

	@Override
	public IBill[] update(IBill[] value) throws BusinessException {
		INewoaspwhMaintain operator = NCLocator.getInstance().lookup(
				INewoaspwhMaintain.class);
		AggNewoaspwhaHeadVO[] vos = operator.update((AggNewoaspwhaHeadVO[]) value);
		return vos;
	}

	@Override
	public IBill[] delete(IBill[] value) throws BusinessException {
		// 目前的删除并不是走这个方法，由于pubapp不支持从这个服务中执行删除操作
		// 单据的删除实际上使用的是：ISingleBillService<AggSingleBill>的operateBill
		INewoaspwhMaintain operator = NCLocator.getInstance().lookup(
				INewoaspwhMaintain.class);
		operator.delete((AggNewoaspwhaHeadVO[]) value);
		return value;
	}
	
	@Override
	public AggNewoaspwhaHeadVO operateBill(AggNewoaspwhaHeadVO bill) throws Exception {
		INewoaspwhMaintain operator = NCLocator.getInstance().lookup(
				INewoaspwhMaintain.class);
		operator.delete(new AggNewoaspwhaHeadVO[] { bill });
		return bill;
	}

	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		INewoaspwhMaintain query = NCLocator.getInstance().lookup(
				INewoaspwhMaintain.class);
		return query.query(queryScheme);
	}

}
