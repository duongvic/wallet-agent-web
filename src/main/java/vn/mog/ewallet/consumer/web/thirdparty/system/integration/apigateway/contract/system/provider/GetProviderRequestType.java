package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.provider;

import java.io.Serializable;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class GetProviderRequestType extends MobiliserRequestType implements Serializable {

  private static final long serialVersionUID = 1L;
  protected Long providerId;

  protected String providerCode;


  public Long getProviderId() {
    return providerId;
  }

  public void setProviderId(Long providerId) {
    this.providerId = providerId;
  }

  public String getProviderCode() {
    return providerCode;
  }

  public void setProviderCode(String providerCode) {
    this.providerCode = providerCode;
  }
}
