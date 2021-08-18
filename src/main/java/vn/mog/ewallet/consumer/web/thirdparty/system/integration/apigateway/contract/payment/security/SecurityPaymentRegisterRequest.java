package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.payment.security;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class SecurityPaymentRegisterRequest extends MobiliserRequestType {
  private static final long serialVersionUID = 1L;

  private Long securityPaymentThreshold;
  private String otp;
  private String referenceId;

  public void setSecurityPaymentThreshold(Long securityPaymentThreshold) {
    this.securityPaymentThreshold = securityPaymentThreshold;
  }

  public Long getSecurityPaymentThreshold() {
    return securityPaymentThreshold;
  }

  public String getOtp() {
    return otp;
  }

  public void setOtp(String otp) {
    this.otp = otp;
  }

  public String getReferenceId() {
    return referenceId;
  }

  public void setReferenceId(String referenceId) {
    this.referenceId = referenceId;
  }
}
