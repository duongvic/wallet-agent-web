package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer;

import java.util.List;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class FindCustomerNameResponse extends MobiliserResponseType{

  private static final long serialVersionUID = 1L;

  protected List<String> merchants;

  public List<String> getMerchants() {
    return merchants;
  }

  public void setMerchants(List<String> merchants) {
    this.merchants = merchants;
  }
}
