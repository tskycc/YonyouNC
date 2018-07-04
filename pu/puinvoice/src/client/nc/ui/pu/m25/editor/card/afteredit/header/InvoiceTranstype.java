package nc.ui.pu.m25.editor.card.afteredit.header;

import java.util.ArrayList;
import java.util.List;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.pub.rule.BusitypeFillRule;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 交易类型编辑后的处理
 * <ul>
 * <li>确定流程(必须确定,否则流程的一些处理，如流程检查函数，无法完成)
 * </ul>
 * 
 * @since 6.0
 * @version 2011-8-3 上午09:24:53
 * @author zhaoyha
 */
public class InvoiceTranstype implements ICardHeadTailAfterEditEventListener {

  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    List<IPURemoteCallCombinator> rccRuleLst =
        new ArrayList<IPURemoteCallCombinator>();
    // 注册远程调用
    this.registerRccRule(rccRuleLst, event);
    // 执行远程调用
    this.doRccRule(rccRuleLst);
  }

  private void doRccRule(List<IPURemoteCallCombinator> rccRuleLst) {
    for (IPURemoteCallCombinator rccRule : rccRuleLst) {
      if (null != rccRule) {
        rccRule.process();
      }
    }
  }

  private void regiest_bizRule(List<IPURemoteCallCombinator> rccRuleLst,
      CardHeadTailAfterEditEvent event) {
    CardEditorHelper billhelper =
        new CardEditorHelper(event.getBillCardPanel());
    // 只有自制的才确定流程
    //拉单可以直接给pk_busitype没问题，可是复制功能就会出现问题，瞎搞
    /*if (AggVOUtil.isSelfMade(billhelper, InvoiceItemVO.CSOURCEID)) {
      BusitypeFillRule biz =
          new BusitypeFillRule(billhelper, POBillType.Invoice.getCode());
      biz.prepare();
      rccRuleLst.add(biz);
    }*/
  }

  private void registerRccRule(List<IPURemoteCallCombinator> rccRuleLst,
      CardHeadTailAfterEditEvent event) {
    // 支持批量远程调用－确定业务流程－注册
    this.regiest_bizRule(rccRuleLst, event);
  }

}
