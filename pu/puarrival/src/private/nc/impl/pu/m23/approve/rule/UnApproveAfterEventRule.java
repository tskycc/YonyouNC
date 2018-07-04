package nc.impl.pu.m23.approve.rule;

import nc.bs.businessevent.BusinessEvent;
import nc.bs.businessevent.EventDispatcher;
import nc.bs.businessevent.IEventType;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.constant.PUMDValue;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 到货单弃审后事件处理
 * @scene
 * 到货单弃审
 * @param
 * 无
 *
 * @since 6.3
 * @version 2013-5-6 下午02:51:09
 * @author fanly3
 */

public class UnApproveAfterEventRule implements IRule<ArriveVO> {

  @Override
  public void process(ArriveVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    try {
      String sourceid = PUMDValue.Arrival.value();
      EventDispatcher.fireEvent(new BusinessEvent(sourceid,
          IEventType.TYPE_UNAPPROV_AFTER, vos));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
