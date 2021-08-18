package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet;

import vn.mog.framework.contract.base.MobiliserResponseType;

public class RequestChargingFundInOverPaymentGatewayV3Response
    extends MobiliserResponseType {
  private static final long serialVersionUID = 1L;

  private String payUrl;
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
