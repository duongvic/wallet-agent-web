package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo;

import java.util.Collection;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.bean.ProviderProfile;
import vn.mog.framework.contract.base.MobiliserResponseType;

@SuppressWarnings("serial")
public class FindProviderProfileResponse extends MobiliserResponseType {

  private Collection<ProviderProfile> providerProfiles;

  public Collection<ProviderProfile> getProviderProfiles() {
    return providerProfiles;
  }

  public void setProviderProfiles(Collection<ProviderProfile> providerProfiles) {
    this.providerProfiles = providerProfiles;
  }
}
