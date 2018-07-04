/***************************************************************
 * \ The skeleton of this class is generated by an automatic * code generator
 * for NC product. It is based on Velocity. * \
 ***************************************************************/
package nc.vo.pu.m25trantype.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.vo.pubapp.enumeration.NCMDEnum;

/**
 * 传应付控制：0入库匹配、1不控制
 * 
 * @author GGR
 * @time 2009-12-22 下午04:33:56
 * @since 6.0
 */
public class SendControlMode extends NCMDEnum {
  // 入库匹配
  public static final SendControlMode MODE_ENTER_STORE_MATCH = MDEnum.valueOf(
      SendControlMode.class, Integer.valueOf(0));

  // 不限制
  public static final SendControlMode MODE_NO_CTRL = MDEnum.valueOf(
      SendControlMode.class, Integer.valueOf(1));

  /**
   * 构造子
   * 
   * @param enumvalue
   */
  public SendControlMode(IEnumValue enumvalue) {
    super(enumvalue);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
