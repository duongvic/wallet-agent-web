package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank;

import vn.mog.framework.contract.base.MobiliserResponseType;

public class CustomerLinkBankResponse extends MobiliserResponseType {
  private static final long serialVersionUID = -8863825838580724094L;

  private String responseTime; // yyyy-MM-ddTHH:mm:ssXXX (UTC DateTime)

  private Boolean isOtp; // True: khi xác thực OTP, False: ko có OTP

  private String subscriptionId; // Dùng để lưu thông tin link ví giữa 1pay và merchant

  private String issueDate; // Ngày cấp subcriptionId (ddMMyy)

  private String expireDate; // Ngày hết hạn (ddMMyy)
  // private String respCode; // Trạng thái giao dịch

  private String redirectUrl; // URL để khách hàng thực hiện liên kết ví

  private String description; // Diễn giải giao dịch

  private String requestId; // Mã giao dịch gửi lên

  public Boolean getRegisterPaymentOnlineRequired() {
    return isRegisterPaymentOnlineRequired;
  }

  public void setRegisterPaymentOnlineRequired(Boolean registerPaymentOnlineRequired) {
    isRegisterPaymentOnlineRequired = registerPaymentOnlineRequired;
  }

  private Boolean isRegisterPaymentOnlineRequired; // Yêu cầu đăng ký Payment Online

  public String getResponseTime() {
    return responseTime;
  }

  public void setResponseTime(String responseTime) {
    this.responseTime = responseTime;
  }

  public Boolean getIsOtp() {
    return isOtp;
  }

  public void setIsOtp(Boolean isOtp) {
    this.isOtp = isOtp;
  }

  public String getSubscriptionId() {
    return subscriptionId;
  }

  public void setSubscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
  }

  public String getIssueDate() {
    return issueDate;
  }

  public void setIssueDate(String issueDate) {
    this.issueDate = issueDate;
  }

  public String getExpireDate() {
    return expireDate;
  }

  public void setExpireDate(String expireDate) {
    this.expireDate = expireDate;
  }

//  public String getRespCode() {
//    return respCode;
//  }
//
//  public void setRespCode(String respCode) {
//    this.respCode = respCode;
//  }

  public String getRedirectUrl() {
    return redirectUrl;
  }

  public void setRedirectUrl(String redirectUrl) {
    this.redirectUrl = redirectUrl;
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


}
