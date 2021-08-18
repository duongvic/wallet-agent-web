package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestBankChargingFundInOverPaymentGatewayRequest {
  private Long amount;

  @JsonProperty("result_url")
  private String resultUrl;

  @JsonProperty("payment_gateway_code")
  private String paymentGatewayCode;

  private String signature;

  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public String getResultUrl() {
    return resultUrl;
  }

  public void setResultUrl(String resultUrl) {
    this.resultUrl = resultUrl;
  }

  public String getPaymentGatewayCode() {
    return paymentGatewayCode;
  }

  public void setPaymentGatewayCode(String paymentGatewayCode) {
    this.paymentGatewayCode = paymentGatewayCode;
  }

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }



}
