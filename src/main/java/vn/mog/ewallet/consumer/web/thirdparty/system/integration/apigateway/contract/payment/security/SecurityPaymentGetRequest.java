package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.payment.security;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class SecurityPaymentGetRequest extends MobiliserRequestType {
  private static final long serialVersionUID = 1L;

  private Long customerId;

  private String cif;

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public String getCif() {
    return cif;
  }

  public void setCif(String cif) {
    this.cif = cif;
  }
}
