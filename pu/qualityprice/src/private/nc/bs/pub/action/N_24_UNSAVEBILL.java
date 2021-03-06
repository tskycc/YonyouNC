package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * 备注：价格结算单的收回 单据动作执行中的动态执行类的动态执行类。 创建日期：(2010-10-12)
 * 
 * @author 平台脚本生成
 */
public class N_24_UNSAVEBILL extends AbstractCompiler2 {

  /**
   * N_24_UNSAVEBILL 构造子注解。
   */
  public N_24_UNSAVEBILL() {
    super();
  }

  /*
   * 备注：平台编写原始脚本
   */
  @Override
  public String getCodeRemark() {
    return "	Object retObj=null;\n	nc.vo.pu.m24.entity.PricestlVO[] inObject = (nc.vo.pu.m24.entity.PricestlVO[]) getVos();\n          Object retValue =nc.bs.framework.common.NCLocator.getInstance().lookup(nc.itf.pu.m24.IPricestlApprove.class).unSendapprove(inObject, this);\n          return retValue;\n";
  }

  /*
   * 备注：平台编写规则类 接口执行类
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      super.m_tmpVo = vo;

      nc.vo.pu.m24.entity.PricestlVO[] inObject =
          (nc.vo.pu.m24.entity.PricestlVO[]) this.getVos();
      Object retValue =
          nc.bs.framework.common.NCLocator.getInstance()
              .lookup(nc.itf.pu.m24.IPricestlApprove.class)
              .unSendapprove(inObject, this);
      return retValue;
    }
    catch (Exception ex) {
      if (ex instanceof BusinessException) {
        throw (BusinessException) ex;
      }

      throw new PFBusinessException(ex.getMessage(), ex);

    }
  }

}
