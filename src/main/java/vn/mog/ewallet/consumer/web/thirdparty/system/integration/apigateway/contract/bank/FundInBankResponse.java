package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank;

public class FundInBankResponse extends BaseResponse {
  private static final long serialVersionUID = 7394014200381556504L;

  private String responseTime;

  private String respCode;

  private String description;

  private String requestId;

  private String bankRecTranId;

  private String bankConTranId;

  private Boolean revertible;

  private Boolean isOtp;

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

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public String getBankRecTranId() {
    return bankRecTranId;
  }

  public void setBankRecTranId(String bankRecTranId) {
    this.bankRecTranId = bankRecTranId;
  }

  public String getBankConTranId() {
    return bankConTranId;
  }

  public void setBankConTranId(String bankConTranId) {
    this.bankConTranId = bankConTranId;
  }

  public Boolean getRevertible() {
    return revertible;
  }

  public void setRevertible(Boolean revertible) {
    this.revertible = revertible;
  }

  public Boolean getIsOtp() {
    return isOtp;
  }

  public void setIsOtp(Boolean isOtp) {
    this.isOtp = isOtp;
  }
}
