package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class GetStoreS3UrlRequest extends MobiliserRequestType {

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  private Long customerId;
  private String cif;

  public String getCif() {
    return cif;
  }

  public void setCif(String cif) {
    this.cif = cif;
  }
}
