package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank;


import com.fasterxml.jackson.annotation.JsonIgnore;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class UnlinkBankResponse extends MobiliserResponseType {

  private static final long serialVersionUID = 6213499898089596999L;

  private String responseTime; // yyyy-MM-ddTHH:mm:ssXXX (UTC DateTime)
  @JsonIgnore
  private String respCode; // Trạng thái giao dịch
  private String description; // Diễn giải
  private Boolean isOtp; // True: có otp, False : không có otp
  private String bankTrxId; // Mã giao dịch ghi nhận tại ngân hàng
  private String requestId; // Mã giao dịch gửi lên

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

  public Boolean getIsOtp() {
    return isOtp;
  }

  public void setIsOtp(Boolean isOtp) {
    this.isOtp = isOtp;
  }

  public String getBankTrxId() {
    return bankTrxId;
  }

  public void setBankTrxId(String bankTrxId) {
    this.bankTrxId = bankTrxId;
  }

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }
}
