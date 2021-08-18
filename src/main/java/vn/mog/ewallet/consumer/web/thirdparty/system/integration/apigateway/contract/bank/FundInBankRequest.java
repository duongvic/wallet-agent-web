package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank;

import org.apache.commons.lang3.StringUtils;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class FundInBankRequest extends MobiliserRequestType {

  private static final long serialVersionUID = 8067283350082930838L;
  private String requestTime;

  private String requestId;

  private String accesskey;

  private String secretkey;

  private String signature;

  private String accountNumber;

  private String walletId;

  private String subscriptionId;

  private String bankCode;

  private String amount;

  private String feeAmount;

  private String verifyRequestId;

  private String type;

  private String otp;

  private String channel;

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

  public String getSubscriptionId() {
    return subscriptionId;
  }

  public void setSubscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
  }

  public String getBankCode() {
    return bankCode;
  }

  public void setBankCode(String bankCode) {
    this.bankCode = bankCode;
  }

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
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
    return StringUtils.trimToEmpty(type);
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getWalletId() {
    return walletId;
  }

  public void setWalletId(String walletId) {
    this.walletId = walletId;
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

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

}
