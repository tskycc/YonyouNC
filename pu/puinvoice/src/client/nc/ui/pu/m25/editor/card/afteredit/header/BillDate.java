/**
 * $文件说明$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-23 上午11:19:51
 */
package nc.ui.pu.m25.editor.card.afteredit.header;

import nc.ui.pu.m25.editor.utils.ExchangeRateUtil;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>表头发票日期编辑后处理
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-3-23 上午11:19:51
 */
public class BillDate implements ICardHeadTailAfterEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener#afterEdit(nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent)
   */
  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    CardEditorHelper util = new CardEditorHelper(event.getBillCardPanel());
    ExchangeRateUtil.changeExchangeRate(util);
  }

}
