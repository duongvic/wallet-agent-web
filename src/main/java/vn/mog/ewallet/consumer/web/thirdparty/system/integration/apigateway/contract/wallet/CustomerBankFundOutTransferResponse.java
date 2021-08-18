package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet;

import java.io.Serializable;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class CustomerBankFundOutTransferResponse extends MobiliserResponseType implements
    Serializable {

  private String requestId;//"": ""000000000269"",
  private String responseTime;//"": ""2018-04-16T11:18:30+07:00"",
  private String respCode;//"": ""000"",
  private String description;//"": ""Success!"",
  private String isOtp;//"": false,
  private String bankRecTranId;//"": null,
  private String bankConTranId;//"": null,
  private String revertible;//"": null,
  private String accountNumber;//"": null,
  private String accountName;//"": null

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public String getResponseTime() {
    return responseTime;
  }

  public void setResponseTime(String responseTime) {
    this.responseTime = responseTime;
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

  public String getIsOtp() {
    return isOtp;
  }

  public void setIsOtp(String isOtp) {
    this.isOtp = isOtp;
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

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }



}
