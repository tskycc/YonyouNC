/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-28 下午03:40:54
 */
package nc.pubitf.pu.m21.arap.mf3;

import nc.vo.pu.pub.writeback.IWriteBackPubPara;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>付款/预付款回写订单的参数接口
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-6-28 下午03:40:54
 */
public interface IOrderWriteBackParaForF3 extends IWriteBackPubPara {
  /**
   * 方法功能描述：订单日期
   * <p>
   * <b>参数说明</b>
   * 
   * @return
   *         <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-30 下午03:25:51
   */
  public UFDate getBilldate();

  /**
   * 方法功能描述：原币币种
   * <p>
   * <b>参数说明</b>
   * 
   * @return
   *         <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-16 下午01:39:19
   */
  public String getCurrency();

  /**
   * 方法功能描述：本币付款金额
   * <p>
   * <b>参数说明</b>
   * 
   * @return
   *         <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-28 下午04:00:17
   */
  public UFDouble getDiffPaymny();

  /**
   * 方法功能描述：付款金额
   * <p>
   * <b>参数说明</b>
   * 
   * @return
   *         <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-28 下午03:59:34
   */
  public UFDouble getDiffPayorgmny();

}
