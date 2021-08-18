package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank;

import vn.mog.framework.contract.base.MobiliserResponseType;

public class RegisterPaymentOnlineResponse extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  private String respCode; // Trạng thái giao dịch

  private String description; // Diễn giải giao dịch

  private Boolean isOtpRequired; // Yêu cầu mã xác thực

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

  public Boolean getIsOtpRequired() {
    return isOtpRequired;
  }

  public void setIsOtpRequired(Boolean isOtpRequired) {
    this.isOtpRequired = isOtpRequired;
  }

}
