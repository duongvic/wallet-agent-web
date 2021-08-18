package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class CustomerLinkBankRequest extends MobiliserRequestType {

  private static final long serialVersionUID = 8530891225119700689L;

  private String requestId; // Số định danh duy nhất trong hệ thống của merchant (M)

  private String requestTime; // yyyy-MM-ddTHH:mm:ssXXX (UTC DateTime) (M)

  private String accesskey; // Mã cấu hình trên hệ thống của 1pay (M)

  private String secretkey; // Mã cấu hình trên hệ thống của 1pay (M)

  private String signature; // Chữ ký bảo mật với hệ thống (RSA-SHA1) (M)

  private String verifyRequestId; // = requestId lần đầu gọi khi xác thực OTP (O)

  private String bankCode; // Mã ngân hàng  (M)

  private String walletId; // ID Khách hàng trên hệ thống của merchant (M)

  private String accountNumber; // linkType = CARD : số thẻ, linkType = ACCOUNT : số tài khoản (M)

  private String ssn; // CMND/passport; (O)

  private String fullName; // Tên khách hàng theo Tài khoản bank account hoặc trên Bank Card (O)

  private String phoneNumber; // Số điện thoại (M)

  private String linkType; // CARD: link theo bank, ACCOUNT: link theo số tài khoản (M)

  private String type; // Verify or Confirm (M)

  private String cardIssueDate; // Ngày phát hành trên thẻ (M)

  private String otp; // otp (C)

  private String channel; // Kênh thực hiện giao dịch(MOBILE,WEB,POS,DESKTOP) (M)

  private String description; // Mô tả (O)

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

  public String getVerifyRequestId() {
    return verifyRequestId;
  }

  public void setVerifyRequestId(String verifyRequestId) {
    this.verifyRequestId = verifyRequestId;
  }

  public String getBankCode() {
    return bankCode;
  }

  public void setBankCode(String bankCode) {
    this.bankCode = bankCode;
  }

  public String getWalletId() {
    return walletId;
  }

  public void setWalletId(String walletId) {
    this.walletId = walletId;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public String getSsn() {
    return ssn;
  }

  public void setSsn(String ssn) {
    this.ssn = ssn;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getLinkType() {
    return linkType;
  }

  public void setLinkType(String linkType) {
    this.linkType = linkType;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getCardIssueDate() {
    return cardIssueDate;
  }

  public void setCardIssueDate(String cardIssueDate) {
    this.cardIssueDate = cardIssueDate;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
