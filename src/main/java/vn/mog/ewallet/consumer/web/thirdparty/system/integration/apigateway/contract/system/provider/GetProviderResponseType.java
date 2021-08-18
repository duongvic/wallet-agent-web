package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.provider;

import java.io.Serializable;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.provider.beans.Provider;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetProviderResponseType extends MobiliserResponseType implements Serializable {

  private static final long serialVersionUID = 1L;
  protected Provider provider;

  public Provider getProvider() {
    return provider;
  }

  public void setProvider(Provider provider) {
    this.provider = provider;
  }
}
