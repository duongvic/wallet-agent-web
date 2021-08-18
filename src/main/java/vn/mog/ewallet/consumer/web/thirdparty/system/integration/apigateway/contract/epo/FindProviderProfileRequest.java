package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo;

import vn.mog.framework.contract.base.MobiliserRequestType;

@SuppressWarnings("serial")
public class FindProviderProfileRequest extends MobiliserRequestType {

  protected String providerCode;

  public String getProviderCode() {
    return providerCode;
  }

  public void setProviderCode(String providerCode) {
    this.providerCode = providerCode;
  }

  public FindProviderProfileRequest(String providerCode) {
    this.providerCode = providerCode;
  }
}
