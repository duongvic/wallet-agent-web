package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.service;

import java.io.Serializable;
import java.util.List;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.service.bean.TrueService;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class FindTrueServiceResponseType extends MobiliserResponseType implements Serializable {
  private static final long serialVersionUID = 1L;
  protected List<TrueService> services;
  protected Long total;
  protected Long all;

  public List<TrueService> getServices() {
    return services;
  }

  public void setServices(List<TrueService> services) {
    this.services = services;
  }

  public Long getTotal() {
    if (total == null)
      total = 0L;
    return total;
  }

  public void setTotal(Long total) {
    this.total = total;
  }

  public Long getAll() {
    if (all == null)
      all = 0L;
    return all;
  }

  public void setAll(Long all) {
    this.all = all;
  }
}
