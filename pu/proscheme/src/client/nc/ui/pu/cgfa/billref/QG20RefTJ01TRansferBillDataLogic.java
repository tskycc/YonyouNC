package nc.ui.pu.cgfa.billref;

import nc.ui.pubapp.billref.dest.DefaultBillDataLogic;

/**
 * 
 * DJ01 参照 DJ02 转单后数据处理类
 * 
 */
public class QG20RefTJ01TRansferBillDataLogic extends DefaultBillDataLogic {

	@Override
	public void doTransferAddLogic(Object selectedData) {

		// 把数据设置在界面上
		super.doTransferAddLogic(selectedData);

//		BillCardPanel cardPanel = this.getBillForm().getBillCardPanel();
//		IKeyValue keyValue = new CardKeyValue(cardPanel);
//
//		// 1.表头合计
//		HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
//		totalrule.calculateHeadTotal();
	}
}