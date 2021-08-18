package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security;

import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.bean.OtpVerificationChannel;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class VerifyForgotPasswordOtpRequest extends MobiliserRequestType {

  private String msisdn;
  private String reference;
  private OtpVerificationChannel type;
  private String otp;

  public String getMsisdn() {
    return msisdn;
  }

  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
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
