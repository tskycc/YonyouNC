package nc.impl.pu.m23.approve.rule;

import nc.bs.businessevent.BusinessEvent;
import nc.bs.businessevent.EventDispatcher;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.event.IArriveEventType;
import nc.vo.pu.pub.constant.PUMDValue;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 到货单收回前事件处理
 * @scene
 * 到货单收回
 * @param
 * 无
 *
 * @since 6.3
 * @version 2013-5-6 下午03:14:01
 * @author fanly3
 */

public class UnSendApproveBeforeEventRule implements IRule<ArriveVO> {

  @Override
  public void process(ArriveVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    try {
      String sourceid = PUMDValue.Arrival.value();
      EventDispatcher.fireEvent(new BusinessEvent(sourceid,
          IArriveEventType.TYPE_UNSENDAPPROVE_BEFORE, vos));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
