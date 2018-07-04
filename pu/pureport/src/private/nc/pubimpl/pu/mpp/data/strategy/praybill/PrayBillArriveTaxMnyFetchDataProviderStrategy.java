package nc.pubimpl.pu.mpp.data.strategy.praybill;

import nc.vo.pub.lang.UFDouble;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.tb.obj.NtbParamVO;

public class PrayBillArriveTaxMnyFetchDataProviderStrategy extends
    PrayBillTaxMnyFetchDataProviderStrategy {
  @Override
  public UFDouble[][] getReadyDataBatch(NtbParamVO[] param) {
    return this.getReadyDataBatch(param, POBillType.Arrive.getCode());
  }
}
