package nc.impl.pu.m21.action.rule.approve;

import nc.bs.businessevent.BusinessEvent;
import nc.bs.businessevent.EventDispatcher;
import nc.bs.businessevent.IEventType;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.constant.PUMDValue;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              采购订单审批前事件处理
 * @scene
 *        采购订单审批
 * @param 无
 * @since 6.3
 * @version 2014-10-20 下午3:38:28
 * @author luojw
 */

public class ApproveBeforeEventRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    try {
      String sourceid = PUMDValue.Order.value();
      EventDispatcher.fireEvent(new BusinessEvent(sourceid,
          IEventType.TYPE_APPROV_BEFORE, vos));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
