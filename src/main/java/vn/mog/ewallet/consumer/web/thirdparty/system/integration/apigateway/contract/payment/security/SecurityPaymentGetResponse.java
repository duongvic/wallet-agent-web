package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.payment.security;

import vn.mog.framework.contract.base.MobiliserResponseType;

public class SecurityPaymentGetResponse extends MobiliserResponseType {
  private static final long serialVersionUID = 1L;

  private Long securityPaymentThreshold;

  public void setSecurityPaymentThreshold(Long securityPaymentThreshold) {
    this.securityPaymentThreshold = securityPaymentThreshold;
  }

  public Long getSecurityPaymentThreshold() {
    return securityPaymentThreshold;
  }
}
