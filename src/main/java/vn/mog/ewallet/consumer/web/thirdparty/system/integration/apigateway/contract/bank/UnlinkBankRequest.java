package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank;

import vn.mog.framework.contract.base.MobiliserRequestType;


public class UnlinkBankRequest extends MobiliserRequestType {

  private static final long serialVersionUID = -4335943106646860709L;

  private String requestTime; // yyyy-MM-ddTHH:mm:ssXXX (UTC DateTime) (M)
  private String requestId; // Số định danh duy nhất trong hệ thống của merchant (M)
  private String accesskey; // Mã cấu hình trên hệ thống 1pay (M)
  private String secretkey; // Mã cấu hình trên hệ thống 1pay (M)
  private String signature; // Dữ liệu được mã hóa theo giải thuật (RSA – SHA1) (M)
  private String subscriptionId; // Số token liên kết giữa merchant và 1pay (M)
  private String walletId; // Mã định danh khách hàng (M)
  private String issueDate; // Ngày cấp subscriptionId (O)
  private String bankCode; // Mã ngân hàng (M)
  private String type; // Verify or Confirm (M)
  private String otp; // Mã OTP (O)
  private String channel; // Kênh thực hiện giao dịch(MOBILE,WEB,POS,DESKTOP) (M)

  public String getRequestTime() {
    return requestTime;
  }

  public void setRequestTime(String requestTime) {
    this.requestTime = requestTime;
  }

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
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

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }

  public String getSubscriptionId() {
    return subscriptionId;
  }

  public void setSubscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
  }

  public String getWalletId() {
    return walletId;
  }

  public void setWalletId(String walletId) {
    this.walletId = walletId;
  }

  public String getIssueDate() {
    return issueDate;
  }

  public void setIssueDate(String issueDate) {
    this.issueDate = issueDate;
  }

  public String getBankCode() {
    return bankCode;
  }

  public void setBankCode(String bankCode) {
    this.bankCode = bankCode;
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
}
