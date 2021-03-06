/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-11 下午06:27:58
 */
package nc.ui.pu.m20.editor.card.afteredit.body;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m20.rule.CalculateBodyDays;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>需求日期编辑后事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-11 下午06:27:58
 */
public class ReqDate implements ICardBodyAfterEditEventListener {

	@Override
	public void afterEdit(CardBodyAfterEditEvent event) {
		this.checkReqDate(event);
	}

	private void checkReqDate(CardBodyAfterEditEvent event) {
		// UFDate billDate = (UFDate) event.getBillCardPanel().getHeadItem(
		// PraybillHeaderVO.DBILLDATE).getValueObject();
		// UFDate reqDate = (UFDate) event.getValue();
		//
		// if (new BillDateAndReqDate().isBillDateAfterReqDate(billDate,
		// reqDate)) {
		// MessageDialog.showWarningDlg(null, "错误", "需求日期不能小于请购日期");
		// }

		CardEditorHelper kv = new CardEditorHelper(event.getBillCardPanel());
		// 需求日期和建议订货日期处理
		boolean isExcelImprting = event.isExcelImprting();
		// 王梓懿 2018-03-28 修改，如果正在excel 导入的话就不这样联动赋值了
		if (isExcelImprting) {
			return;
		}
		new CalculateBodyDays().setAdvDays(kv, new int[] { event.getRow() });
	}
}
