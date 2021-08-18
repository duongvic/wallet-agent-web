package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.provider;

import java.io.Serializable;

public class ChangeProviderStatusRequest extends ChangeProviderStatusRequestType implements
    Serializable {

  private static final long serialVersionUID = 1L;

  public ChangeProviderStatusRequest(Long providerId, Boolean active) {
    this.providerId = providerId;
    this.active = active;
  }

  public ChangeProviderStatusRequest() {

  }
}
