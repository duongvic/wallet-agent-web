package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.service;

import java.util.Map;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.service.bean.TrueService;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.service.bean.TrueServiceCommissionFeeDetail;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class TrueServiceCommissionGetResponse extends MobiliserResponseType {
  private static final long serialVersionUID = 1L;

  private TrueService service;
  private Map<Integer, Double> commissionPercentageMap; // FaceValue/ comissionRate
  private Map<Integer, TrueServiceCommissionFeeDetail> commissionFeeMap;

  public TrueService getService() {
    return service;
  }

  public void setService(TrueService service) {
    this.service = service;
  }

  public Map<Integer, Double> getCommissionPercentageMap() {
    return commissionPercentageMap;
  }

  public void setCommissionPercentageMap(Map<Integer, Double> commissionPercentageMap) {
    this.commissionPercentageMap = commissionPercentageMap;
  }

  public Map<Integer, TrueServiceCommissionFeeDetail> getCommissionFeeMap() {
    return commissionFeeMap;
  }

  public void setCommissionFeeMap(Map<Integer, TrueServiceCommissionFeeDetail> commissionFeeMap) {
    this.commissionFeeMap = commissionFeeMap;
  }
}
