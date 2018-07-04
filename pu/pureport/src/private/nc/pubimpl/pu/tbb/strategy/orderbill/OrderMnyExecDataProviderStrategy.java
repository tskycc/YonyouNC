package nc.pubimpl.pu.tbb.strategy.orderbill;

import nc.pubimpl.pu.tbb.strategy.IExecDataProviderStrategy;
import nc.vo.pu.tbb.PuBillBusiSysReg;
import nc.vo.pub.lang.UFDouble;
import nc.vo.tb.obj.NtbParamVO;

/**
 * �ɹ�������˰���
 * 
 * @since 6.0
 * @version 2011-3-7 ����06:28:31
 * @author yinfy
 */

public class OrderMnyExecDataProviderStrategy implements
    IExecDataProviderStrategy {

  @Override
  public UFDouble[][] getExecDataBatch(NtbParamVO[] param) throws Exception {
    OrderBillGetDataBatchDMO dmo = new OrderBillGetDataBatchDMO();
    return dmo.getExecDataBatch(param, PuBillBusiSysReg.NMNY);
  }

}