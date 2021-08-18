package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security;


import vn.mog.framework.contract.base.MobiliserResponseType;

public class ForgetPasswordOtpGetResponse extends MobiliserResponseType {
  private static final long serialVersionUID = 1L;

  private String reference;

  public String getReference() {
    return reference;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }
}
