package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security;


import vn.mog.framework.contract.base.MobiliserRequestType;

public class ForgetPasswordOtpGetRequest extends MobiliserRequestType {
  private static final long serialVersionUID = 1L;

  private String phoneNumber;

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
}
