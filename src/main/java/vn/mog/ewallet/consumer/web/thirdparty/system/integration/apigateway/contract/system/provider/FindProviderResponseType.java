package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.provider;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.provider.beans.Provider;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class FindProviderResponseType extends MobiliserResponseType implements Serializable {

  private static final long serialVersionUID = 1L;
  protected List<Provider> providers;
  protected Long total;
  protected Long all;

  public List<Provider> getProviders() {
    if (providers == null) {
      providers = Collections.emptyList();
    }
    return providers;
  }

  public void setProviders(List<Provider> providers) {
    this.providers = providers;
  }

  public Long getTotal() {
    if (total == null) {
      total = 0L;
    }
    return total;
  }

  public void setTotal(Long total) {
    this.total = total;
  }

  public Long getAll() {
    return all;
  }

  public void setAll(Long all) {
    this.all = all;
  }
}
