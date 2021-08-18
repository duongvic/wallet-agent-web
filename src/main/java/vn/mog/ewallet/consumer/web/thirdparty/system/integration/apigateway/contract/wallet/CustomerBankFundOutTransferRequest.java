package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet;

import com.fasterxml.jackson.annotation.JsonProperty;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class CustomerBankFundOutTransferRequest extends MobiliserRequestType {

  private String requestId;//"": ""{{requestId}}"",

  private String requestTime;//"": ""{{requestTime}}"",

  private String accessKey;//"": ""config"",

  private String signature;//"": ""config"",

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public String getRequestTime() {
    return requestTime;
  }

  public void setRequestTime(String requestTime) {
    this.requestTime = requestTime;
  }

  public String getAccessKey() {
    return accessKey;
  }

  public void setAccessKey(String accessKey) {
    this.accessKey = accessKey;
  }

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }

  public String getSecretKey() {
    return secretKey;
  }

  public void setSecretKey(String secretKey) {
    this.secretKey = secretKey;
  }

  public String getWalletId() {
    return walletId;
  }

  public void setWalletId(String walletId) {
    this.walletId = walletId;
  }

  public String getBankCode() {
    return bankCode;
  }

  public void setBankCode(String bankCode) {
    this.bankCode = bankCode;
  }

  public String getLinkType() {
    return linkType;
  }

  public void setLinkType(String linkType) {
    this.linkType = linkType;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public String getFeeAmount() {
    return feeAmount;
  }

  public void setFeeAmount(String feeAmount) {
    this.feeAmount = feeAmount;
  }

  public String getVerifyRequestId() {
    return verifyRequestId;
  }

  public void setVerifyRequestId(String verifyRequestId) {
    this.verifyRequestId = verifyRequestId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getOtp() {
    return otp;
  }

  public void setOtp(String otp) {
    this.otp = otp;
  }

  public String getChannel() {
    return channel;
  }

  public void setChannel(String channel) {
    this.channel = channel;
  }

  private String secretKey;//"": ""config"",

  private String walletId;//"": ""0915759946"",

  private String bankCode;//"": ""SGTTVNVX"",

  private String linkType;//"": ""CARD"",

  private String accountNumber;//"": ""9704030123456789"",
  private String amount;//"": 600000,

  @JsonProperty("fee_amount")
  private String feeAmount;//"": 1000,

  @JsonProperty("verify_request_id")
  private String verifyRequestId;//"": null,
  private String type;//"": ""verify"",
  private String otp;//"": null,
  private String channel;//"": ""WEB""

}
