package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * ��ע���ɹ����������
 * ���ݶ���ִ���еĶ�ִ̬����Ķ�ִ̬���ࡣ
 * �������ڣ�(2010-1-26)
 * 
 * @author ƽ̨�ű�����
 */
public class N_21_APPROVE extends AbstractCompiler2 {
  // private Hashtable m_keyHas = null;
  //
  // private java.util.Hashtable m_methodReturnHas = new java.util.Hashtable();

  /**
   * N_21_APPROVE ������ע�⡣
   */
  public N_21_APPROVE() {
    super();
  }

  /*
   * ��ע��ƽ̨��дԭʼ�ű�
   */
  @Override
  public String getCodeRemark() {
    return "	nc.vo.pu.m21.entity.OrderVO[] inObject  =(nc.vo.pu.m21.entity.OrderVO[])getVos ();\nObject retValue=nc.bs.framework.common.NCLocator.getInstance().lookup(nc.itf.pu.m21.IOrderApprove.class).approve(inObject, this);\nreturn retValue;\n";
  }

  /*
   * ��ע��ƽ̨��д������ �ӿ�ִ����
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      super.m_tmpVo = vo;
      nc.vo.pu.m21.entity.OrderVO[] inObject =
          (nc.vo.pu.m21.entity.OrderVO[]) this.getVos();
      Object retValue =
          nc.bs.framework.common.NCLocator.getInstance()
              .lookup(nc.itf.pu.m21.IOrderApprove.class)
              .approve(inObject, this);
      return retValue;
    }
    catch (Exception ex) {
      if (ex instanceof BusinessException) {
        throw (BusinessException) ex;
      }

      throw new PFBusinessException(ex.getMessage(), ex);
    }
  }

  /*
   * ��ע�����ýű�������HAS
   */
  // private void setParameter(String key, Object val) {
  // if (this.m_keyHas == null) {
  // this.m_keyHas = new Hashtable();
  // }
  // if (val != null) {
  // this.m_keyHas.put(key, val);
  // }
  // }
}