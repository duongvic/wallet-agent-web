package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class FundOutNoLinkToBankRequest extends MobiliserRequestType {

  private String signature; // Chữ ký được mã hóa theo giải thuật SHA256 (M)

  private String accesskey; // Mã merchant ,được cấu hình trên hệ thống 1Pay (M)

  private String walletId; // Số ví (M)

  private String bankCode; // Mã ngân hàng cần chuyển tới (M)

  private String amount; // Số tiền (M)

  private String requestId; // Mã giao dịch(không được trùng lặp) (M)

  private String verifyRequestId; // Mã giao dịch gửi lần đầu = requestId (Khi gọi lần confirm) (O)

  private String type; // Verify or Confirm (M)

  private String transferType; // ACCOUNT or CARD

  private String accountNumber; // Số tài khoản ngân hàng (M)

  private String cardNumber; // Số tài khoản ngân hàng (M)

  private String description; // Mô tả giao dịch

  private String orderId; // OTP dùng cho giao dịch

  private String otp; // OTP dùng cho giao dịch

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }

  public String getAccesskey() {
    return accesskey;
  }

  public void setAccesskey(String accesskey) {
    this.accesskey = accesskey;
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

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getTransferType() {
    return transferType;
  }

  public void setTransferType(String transferType) {
    this.transferType = transferType;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public String getOtp() {
    return otp;
  }

  public void setOtp(String otp) {
    this.otp = otp;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }
}
