package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.provider;

import java.io.Serializable;

public class GetProviderRequest extends GetProviderRequestType implements Serializable {

  private static final long serialVersionUID = 1L;

  public GetProviderRequest(String providerCode) {
    this.providerCode = providerCode;
  }

  public GetProviderRequest() {

  }
}
