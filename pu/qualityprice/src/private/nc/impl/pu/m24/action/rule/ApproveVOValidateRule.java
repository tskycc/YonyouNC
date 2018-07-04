package nc.impl.pu.m24.action.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              价格结算单单据状态合法性检查
 * @scene
 *        价格结算单审批
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午4:13:34
 * @author luojw
 */
public class ApproveVOValidateRule implements IRule<PricestlVO> {

  @Override
  public void process(PricestlVO[] vos) {
    // 单据状态合法性检查
    this.statusCheck(vos);
  }

  private void statusCheck(PricestlVO[] vos) {
    for (PricestlVO vo : vos) {
      // 状态是自由或审批中才能进行审批
      if (vo.getHVO().getFbillstatus().intValue() != POEnumBillStatus.FREE
          .toInt()
          && vo.getHVO().getFbillstatus().intValue() != POEnumBillStatus.APPROVING
              .toInt()) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004070_0", "04004070-0002")/*
                                                                     * @res
                                                                     * "单据状态不正确，不能审批！"
                                                                     */);
      }
    }
  }
}
