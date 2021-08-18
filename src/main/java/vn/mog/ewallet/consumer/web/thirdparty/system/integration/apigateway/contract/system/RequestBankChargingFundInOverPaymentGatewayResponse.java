package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestBankChargingFundInOverPaymentGatewayResponse {

  @JsonProperty("pay_url")
  private String payUrl;

  @JsonProperty("order_id")
  private Long orderId;

  private String signature;

  public String getPayUrl() {
    return payUrl;
  }

  public void setPayUrl(String payUrl) {
    this.payUrl = payUrl;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }
}
