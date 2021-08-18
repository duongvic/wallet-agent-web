package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet;


import org.apache.commons.lang3.StringUtils;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class ConfirmOtpFundOutToBankRequest extends MobiliserRequestType {

  private String requestId;

  private String requestTime;

  private String signature;

  private String description;

  private String accesskey;

  private String secretkey;

  private String walletId;

  private String subscriptionId;

  private String verifyRequestId;

  private String channel;

  private String amount;

  private String feeAmount;

  private String type;

  private String otp;

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }

  public String getRequestTime() {
    return requestTime;
  }

  public void setRequestTime(String requestTime) {
    this.requestTime = requestTime;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getSubscriptionId() {
    return subscriptionId;
  }

  public void setSubscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
  }

  public String getAccesskey() {
    return accesskey;
  }

  public void setAccesskey(String accesskey) {
    this.accesskey = accesskey;
  }

  public String getSecretkey() {
    return secretkey;
  }

  public void setSecretkey(String secretkey) {
    this.secretkey = secretkey;
  }

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public String getVerifyRequestId() {
    return verifyRequestId;
  }

  public void setVerifyRequestId(String verifyRequestId) {
    this.verifyRequestId = verifyRequestId;
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

  public String getType() {
    return StringUtils.trimToEmpty(type);
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

  public void setWalletId(String walletId) {
    this.walletId = walletId;
  }

  public String getWalletId() {
    return walletId;
  }
}
