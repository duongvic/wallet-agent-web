package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.provider;

import java.io.Serializable;

public class ChangeProviderServiceStatusRequest extends ChangeProviderServiceStatusRequestType
    implements Serializable {

  private static final long serialVersionUID = 1L;

  public ChangeProviderServiceStatusRequest(Long providerServiceId, Boolean active) {
    this.providerServiceId = providerServiceId;
    this.active = active;
  }

  public ChangeProviderServiceStatusRequest() {

  }
}
