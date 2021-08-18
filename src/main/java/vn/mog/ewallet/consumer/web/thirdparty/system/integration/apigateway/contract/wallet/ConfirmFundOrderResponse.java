package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConfirmFundOrderResponse extends ConfirmFundOrderResponseType {

  private String requestTime;//"": null,
  private String respCode;//"": null,
  private String description;//"": null,
  private String requestId;//"": ""01635055068_verify_636577609423895065"",
  private String bankRecTranId;//"": null,
  private String bankConTranId;//"": null,
  private String revertible;//"": null,
  private String isOtp;//"": null

  public String getRequestTime() {
    return requestTime;
  }

  public void setRequestTime(String requestTime) {
    this.requestTime = requestTime;
  }

  public String getRespCode() {
    return respCode;
  }

  public void setRespCode(String respCode) {
    this.respCode = respCode;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public String getBankRecTranId() {
    return bankRecTranId;
  }

  public void setBankRecTranId(String bankRecTranId) {
    this.bankRecTranId = bankRecTranId;
  }

  public String getBankConTranId() {
    return bankConTranId;
  }

  public void setBankConTranId(String bankConTranId) {
    this.bankConTranId = bankConTranId;
  }

  public String getRevertible() {
    return revertible;
  }

  public void setRevertible(String revertible) {
    this.revertible = revertible;
  }

  public String getIsOtp() {
    return isOtp;
  }

  public void setIsOtp(String isOtp) {
    this.isOtp = isOtp;
  }
}
