package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security;

import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.bean.OtpVerificationChannel;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class ForgotPasswordOtpVerifyRequest extends MobiliserRequestType {
  private static final long serialVersionUID = 1L;
  
  private String phoneNumber;
  private String reference;
  private OtpVerificationChannel type;
  private String otp;

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getReference() {
    return reference;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

  public OtpVerificationChannel getType() {
    return type;
  }

  public void setType(OtpVerificationChannel type) {
    this.type = type;
  }

  public String getOtp() {
    return otp;
  }

  public void setOtp(String otp) {
    this.otp = otp;
  }

}
