package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank;

import vn.mog.framework.contract.base.MobiliserResponseType;

public class FundOutNoLinkToBankResponse extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  private String respCode; // Trạng thái giao dịch

  private String description; // Diễn giải giao dịch

  private String requestId; // Mã requestId merchant truyền sang

  private String accountName; // Tên tài khoản chuyển tới

  private String cardName; // Tên thẻ chuyển tới

  private Boolean isOtpRequired; // Yêu cầu mật khẩu hay không

  private Long orderId;

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

  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

  public String getCardName() {
    return cardName;
  }

  public void setCardName(String cardName) {
    this.cardName = cardName;
  }

  public Boolean getIsOtpRequired() {
    return isOtpRequired;
  }

  public void setIsOtpRequired(Boolean isOtpRequired) {
    this.isOtpRequired = isOtpRequired;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }
}
