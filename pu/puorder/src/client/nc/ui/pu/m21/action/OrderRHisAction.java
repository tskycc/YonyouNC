package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m20.IPraybillRevise;
import nc.itf.pu.m21.IOrderEditRecordQuery;
import nc.ui.pu.m21.view.OrderReviseHistoryDlg;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.model.AbstractUIAppModel;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * 采购订单修订历史版本Action
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>显示历史版本信息
 * </ul>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2009-12-28 下午04:33:56
 */
public class OrderRHisAction extends NCAction {

	private static final long serialVersionUID = 2588413990103479642L;

	/** 列表视图 **/
	private ShowUpableBillListView editor;

	/** 历史版本对话框 **/
	private OrderReviseHistoryDlg hisDialog;

	/** 管理应用模型 **/
	private AbstractUIAppModel model = null;

	/**
	 * 构造子
	 */
	public OrderRHisAction() {
		SCMActionInitializer
				.initializeAction(this, SCMActionCode.SCM_REVISEHISTORY);
		// String name = "修订历史";
		// this.setBtnName(name);
		// this.putValue(INCAction.CODE, name);
		// this.putValue(Action.SHORT_DESCRIPTION, name);
		//
		// 快捷键，确认
		// int modifiers = Event.ALT_MASK;
		// this.putValue(Action.ACCELERATOR_KEY,
		// KeyStroke.getKeyStroke('H', modifiers));
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		try {
			OrderVO ordervo = (OrderVO) this.editor.getModel().getSelectedData();
			OrderVO[] editRecords = null;
			if (ordervo == null) {
				ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
						.getNCLangRes().getStrByID("4004030_0", "04004030-0039")/*
																																		 * @res
																																		 * "请选择要查询的数据"
																																		 */);
			}
			OrderItemVO[] bvos = ordervo.getBVO();
			List<String> pkList = new ArrayList<String>();
			for (OrderItemVO bvo : bvos) {
				pkList.add(bvo.getPk_order_b());
			}
			IOrderEditRecordQuery service = NCLocator.getInstance().lookup(
					IOrderEditRecordQuery.class);
			editRecords = service.queryOrderPrice(pkList);
			this.getHisDialog().setVOs(editRecords);
			this.getHisDialog().showModal();
		} catch (Exception e1) {
			ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
					.getNCLangRes().getStrByID("4004020_0", "04004020-0070")/*
																																	 * @res
																																	 * "显示历史信息出错"
																																	 */);
		}
	}

	/**
	 * 取得列表视图
	 * 
	 * @return 列表视图
	 */
	public ShowUpableBillListView getEditor() {
		return this.editor;
	}

	/**
	 * 取得历史信息对话框
	 * 
	 * @return 历史信息对话框
	 */
	public OrderReviseHistoryDlg getHisDialog() {

		if (this.hisDialog == null) {
			String pk_org = this.model.getContext().getPk_group();
			String title = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
					"40040402", "1400404020004")/* @res "采购订单修订历史" */;
			this.hisDialog = new OrderReviseHistoryDlg(this.model.getContext()
					.getEntranceUI(), pk_org, title);
		}
		return this.hisDialog;
	}

	/**
	 * 取得管理应用模型
	 * 
	 * @return 管理应用模型
	 */
	public AbstractUIAppModel getModel() {
		return this.model;
	}

	/**
	 * 取得请购单修订操作服务
	 * 
	 * @return 请购单修订操作服务
	 */
	public IPraybillRevise getService() {
		return NCLocator.getInstance().lookup(IPraybillRevise.class);
	}

	/**
	 * 设置列表视图。
	 * 
	 * @param editor
	 *          列表视图
	 */
	public void setEditor(ShowUpableBillListView editor) {
		this.editor = editor;
	}

	/**
	 * 设置管理应用模型。
	 * 
	 * @param model
	 *          管理应用模型
	 */
	public void setModel(AbstractUIAppModel model) {
		this.model = model;
		this.model.addAppEventListener(this);
	}

	@Override
	protected boolean isActionEnable() {

		if (null == this.editor) {
			return false;
		}

		int selectRowCount = this.editor.getBillListPanel().getHeadTable()
				.getSelectedRowCount();
		// 历史查询
		if (selectRowCount != 1) {
			return false;
		}

		if (((Integer) this.editor
				.getBillListPanel()
				.getHeadBillModel()
				.getValueAt(
						this.editor.getBillListPanel().getHeadTable().getSelectedRow(),
						"nversion")).floatValue() < 2) {
			return false;
		}

		return true;

	}

}
