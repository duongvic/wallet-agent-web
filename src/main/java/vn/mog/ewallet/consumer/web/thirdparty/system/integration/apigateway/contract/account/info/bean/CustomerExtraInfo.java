package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info.bean;

import java.util.Date;

public class CustomerExtraInfo {

  private Date lastLogin;
  private Date lastChangedLeve1Kyc;
  private Date lastChangedLeve2Kyc;
  private Date lastChangedPassword;
  private Date lastChangedPaymentPin;
  private Detail detail;

  public Date getLastLogin() {
    return lastLogin;
  }

  public void setLastLogin(Date lastLogin) {
    this.lastLogin = lastLogin;
  }

  public Date getLastChangedLeve1Kyc() {
    return lastChangedLeve1Kyc;
  }

  public void setLastChangedLeve1Kyc(Date lastChangedLeve1Kyc) {
    this.lastChangedLeve1Kyc = lastChangedLeve1Kyc;
  }

  public Date getLastChangedLeve2Kyc() {
    return lastChangedLeve2Kyc;
  }

  public void setLastChangedLeve2Kyc(Date lastChangedLeve2Kyc) {
    this.lastChangedLeve2Kyc = lastChangedLeve2Kyc;
  }

  public Date getLastChangedPassword() {
    return lastChangedPassword;
  }

  public void setLastChangedPassword(Date lastChangedPassword) {
    this.lastChangedPassword = lastChangedPassword;
  }

  public Date getLastChangedPaymentPin() {
    return lastChangedPaymentPin;
  }

  public void setLastChangedPaymentPin(Date lastChangedPaymentPin) {
    this.lastChangedPaymentPin = lastChangedPaymentPin;
  }

  public Detail getDetail() {
    return detail;
  }

  public void setDetail(Detail detail) {
    this.detail = detail;
  }


}
