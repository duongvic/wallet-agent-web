package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class ConfirmOtpFundOutToBankResponse extends MobiliserResponseType implements Serializable {

  private String requestId;

  private String responseTime;

  private String respCode;

  private String description;

  private Boolean isOtp;

  private String bankRecTranId;

  private String bankConTranId;

  private Boolean revertible;

  private String accountNumber;

  private String accountName;

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

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

}
