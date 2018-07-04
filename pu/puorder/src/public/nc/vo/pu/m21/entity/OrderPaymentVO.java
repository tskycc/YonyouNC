/***************************************************************\
 *     The skeleton of this class is generated by an automatic *
 * code generator for NC product. It is based on Velocity.     *
\***************************************************************/
package nc.vo.pu.m21.entity;

import nc.vo.pu.m21.enumeration.EffectMonthEnum;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> 在此处简要描述此类的功能 </b>
 * <p>
 * 在此处添加此类的描述信息
 * </p>
 * 创建日期:
 * 
 * @author
 * @version NCPrj ??
 */
@SuppressWarnings("serial")
public class OrderPaymentVO extends SuperVO {
  /**
   * 出账日
   */
  public static final String ACCOUNTDAY = "accountday";

  /**
   * 付款比例
   */
  public static final String ACCRATE = "accrate";

  /**
   * 固定结账日
   */
  public static final String CHECKDATA = "checkdata";

  /**
   * 附加月
   */
  public static final String EFFECTADDMONTH = "effectaddmonth";

  /**
   * 起效日期延迟天数
   */
  public static final String EFFECTDATEADDDATE = "effectdateadddate";

  /**
   * 生效月
   */
  public static final String EFFECTMONTH = "effectmonth";

  /**
   * 质保金
   */
  public static final String ISDEPOSIT = "isdeposit";

  /**
   * 账期天数
   */
  public static final String PAYMENTDAY = "paymentday";

  /**
   * 结算方式
   */
  public static final String PK_BALATYPE = "pk_balatype";

  /**
   * 上层单据主键
   */
  public static final String PK_ORDER = "pk_order";

  /**
   * 付款账期
   */
  public static final String PK_PAYMENT = "pk_payment";

  /**
   * 起效日期
   */
  public static final String PK_PAYPERIOD = "pk_payperiod";

  /**
   * 现金折扣
   */
  public static final String PK_RATE = "pk_rate";

  /**
   * 预付款
   */
  public static final String PREPAYMENT = "prepayment";

  /**
   * 付款期
   */
  public static final String SHOWORDER = "showorder";

  /**
   * 付款协议页签名
   */
  public static final String TABSNAME = "pu_order_payment";

  /**
   * 时间戳
   */
  public static final String TS = "ts";

  /**
   * 按照默认方式创建构造子.
   * 创建日期:
   */
  public OrderPaymentVO() {
    super();
  }

  /**
   * <p>
   * 返回表名称.
   * <p>
   * 创建日期:
   * 
   * @return java.lang.String
   */
  public static java.lang.String getDefaultTableName() {
    return "po_order_payment";
  }

  /**
   * 获取出账日
   * 
   * @return 出账日
   */
  public Integer getAccountday() {
    return (Integer) this.getAttributeValue(OrderPaymentVO.ACCOUNTDAY);
  }

  /**
   * 获取付款比例
   * 
   * @return 付款比例
   */
  public UFDouble getAccrate() {
    return (UFDouble) this.getAttributeValue(OrderPaymentVO.ACCRATE);
  }

  /**
   * 获取固定结账日
   * 
   * @return 固定结账日
   */
  public Integer getCheckdata() {
    return (Integer) this.getAttributeValue(OrderPaymentVO.CHECKDATA);
  }

  /**
   * 获取附加月
   * 
   * @return 附加月
   */
  public Integer getEffectaddmonth() {
    return (Integer) this.getAttributeValue(OrderPaymentVO.EFFECTADDMONTH);
  }

  /**
   * 获取起效日期延迟天数
   * 
   * @return 起效日期延迟天数
   */
  public Integer getEffectdateadddate() {
    return (Integer) this.getAttributeValue(OrderPaymentVO.EFFECTDATEADDDATE);
  }

  /**
   * 获取生效月
   * 
   * @return 生效月
   * @see EffectMonthEnum
   */
  public Integer getEffectmonth() {
    return (Integer) this.getAttributeValue(OrderPaymentVO.EFFECTMONTH);
  }

  /**
   * 获取质保金
   * 
   * @return 质保金
   */
  public UFBoolean getIsdeposit() {
    return (UFBoolean) this.getAttributeValue(OrderPaymentVO.ISDEPOSIT);
  }

  @Override
  @nc.vo.annotation.MDEntityInfo(beanFullclassName = "nc.vo.pu.m21.entity.OrderPaymentVO")
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("pu.po_order_payment");
  }

  /**
   * <p>
   * 取得父VO主键字段.
   * <p>
   * 创建日期:
   * 
   * @return java.lang.String
   */
  @Override
  public java.lang.String getParentPKFieldName() {
    return null;
  }

  /**
   * 获取账期天数
   * 
   * @return 账期天数
   */
  public Integer getPaymentday() {
    return (Integer) this.getAttributeValue(OrderPaymentVO.PAYMENTDAY);
  }

  /**
   * 获取结算方式
   * 
   * @return 结算方式
   */
  public String getPk_balatype() {
    return (String) this.getAttributeValue(OrderPaymentVO.PK_BALATYPE);
  }

  /**
   * 获取上层单据主键
   * 
   * @return 上层单据主键
   */
  public String getPk_order() {
    return (String) this.getAttributeValue(OrderPaymentVO.PK_ORDER);
  }

  /**
   * 获取付款账期
   * 
   * @return 付款账期
   */
  public String getPk_payment() {
    return (String) this.getAttributeValue(OrderPaymentVO.PK_PAYMENT);
  }

  /**
   * 获取起效日期
   * 
   * @return 起效日期
   */
  public String getPk_payperiod() {
    return (String) this.getAttributeValue(OrderPaymentVO.PK_PAYPERIOD);
  }

  /**
   * 获取现金折扣
   * 
   * @return 现金折扣
   */
  public String getPk_rate() {
    return (String) this.getAttributeValue(OrderPaymentVO.PK_RATE);
  }

  /**
   * <p>
   * 取得表主键.
   * <p>
   * 创建日期:
   * 
   * @return java.lang.String
   */
  @Override
  public java.lang.String getPKFieldName() {
    return "pk_payment";
  }

  /**
   * 获取预付款
   * 
   * @return 预付款
   */
  public UFBoolean getPrepayment() {
    return (UFBoolean) this.getAttributeValue(OrderPaymentVO.PREPAYMENT);
  }

  /**
   * 获取付款期
   * 
   * @return 付款期
   */
  public Integer getShoworder() {
    return (Integer) this.getAttributeValue(OrderPaymentVO.SHOWORDER);
  }

  /**
   * <p>
   * 返回表名称.
   * <p>
   * 创建日期:
   * 
   * @return java.lang.String
   */
  @Override
  public java.lang.String getTableName() {
    return "po_order_payment";
  }

  /**
   * 获取时间戳
   * 
   * @return 时间戳
   */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(OrderPaymentVO.TS);
  }

  /**
   * 设置出账日
   * 
   * @param accountday 出账日
   */
  public void setAccountday(Integer accountday) {
    this.setAttributeValue(OrderPaymentVO.ACCOUNTDAY, accountday);
  }

  /**
   * 设置付款比例
   * 
   * @param accrate 付款比例
   */
  public void setAccrate(UFDouble accrate) {
    this.setAttributeValue(OrderPaymentVO.ACCRATE, accrate);
  }

  /**
   * 设置固定结账日
   * 
   * @param checkdata 固定结账日
   */
  public void setCheckdata(Integer checkdata) {
    this.setAttributeValue(OrderPaymentVO.CHECKDATA, checkdata);
  }

  /**
   * 设置附加月
   * 
   * @param effectaddmonth 附加月
   */
  public void setEffectaddmonth(Integer effectaddmonth) {
    this.setAttributeValue(OrderPaymentVO.EFFECTADDMONTH, effectaddmonth);
  }

  /**
   * 设置起效日期延迟天数
   * 
   * @param effectdateadddate 起效日期延迟天数
   */
  public void setEffectdateadddate(Integer effectdateadddate) {
    this.setAttributeValue(OrderPaymentVO.EFFECTDATEADDDATE, effectdateadddate);
  }

  /**
   * 设置生效月
   * 
   * @param effectmonth 生效月
   * @see EffectMonthEnum
   */
  public void setEffectmonth(Integer effectmonth) {
    this.setAttributeValue(OrderPaymentVO.EFFECTMONTH, effectmonth);
  }

  /**
   * 设置质保金
   * 
   * @param isdeposit 质保金
   */
  public void setIsdeposit(UFBoolean isdeposit) {
    this.setAttributeValue(OrderPaymentVO.ISDEPOSIT, isdeposit);
  }

  /**
   * 设置账期天数
   * 
   * @param paymentday 账期天数
   */
  public void setPaymentday(Integer paymentday) {
    this.setAttributeValue(OrderPaymentVO.PAYMENTDAY, paymentday);
  }

  /**
   * 设置结算方式
   * 
   * @param pk_balatype 结算方式
   */
  public void setPk_balatype(String pk_balatype) {
    this.setAttributeValue(OrderPaymentVO.PK_BALATYPE, pk_balatype);
  }

  /**
   * 设置上层单据主键
   * 
   * @param pk_order 上层单据主键
   */
  public void setPk_order(String pk_order) {
    this.setAttributeValue(OrderPaymentVO.PK_ORDER, pk_order);
  }

  /**
   * 设置付款账期
   * 
   * @param pk_payment 付款账期
   */
  public void setPk_payment(String pk_payment) {
    this.setAttributeValue(OrderPaymentVO.PK_PAYMENT, pk_payment);
  }

  /**
   * 设置起效日期
   * 
   * @param pk_payperiod 起效日期
   */
  public void setPk_payperiod(String pk_payperiod) {
    this.setAttributeValue(OrderPaymentVO.PK_PAYPERIOD, pk_payperiod);
  }

  /**
   * 设置现金折扣
   * 
   * @param pk_rate 现金折扣
   */
  public void setPk_rate(String pk_rate) {
    this.setAttributeValue(OrderPaymentVO.PK_RATE, pk_rate);
  }

  /**
   * 设置预付款
   * 
   * @param prepayment 预付款
   */
  public void setPrepayment(UFBoolean prepayment) {
    this.setAttributeValue(OrderPaymentVO.PREPAYMENT, prepayment);
  }

  /**
   * 设置付款期
   * 
   * @param showorder 付款期
   */
  public void setShoworder(Integer showorder) {
    this.setAttributeValue(OrderPaymentVO.SHOWORDER, showorder);
  }

  /**
   * 设置时间戳
   * 
   * @param ts 时间戳
   */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(OrderPaymentVO.TS, ts);
  }
}
