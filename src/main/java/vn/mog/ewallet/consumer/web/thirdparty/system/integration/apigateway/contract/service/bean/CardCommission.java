package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.service.bean;

import java.util.Map;
import java.util.List;

public class CardCommission {

  private List<String> prices;
  private Map<Integer, TrueServiceCommissionFeeDetail> commissionFee;
  private String serviceDesc;

  public List<String> getPrices() {
    return prices;
  }

  public void setPrices(List<String> prices) {
    this.prices = prices;
  }

  public Map<Integer, TrueServiceCommissionFeeDetail> getCommissionFee() {
    return commissionFee;
  }

  public void setCommissionFee(
      Map<Integer, TrueServiceCommissionFeeDetail> commissionFee) {
    this.commissionFee = commissionFee;
  }

  public String getServiceDesc() {
    return serviceDesc;
  }

  public void setServiceDesc(String serviceDesc) {
    this.serviceDesc = serviceDesc;
  }
}