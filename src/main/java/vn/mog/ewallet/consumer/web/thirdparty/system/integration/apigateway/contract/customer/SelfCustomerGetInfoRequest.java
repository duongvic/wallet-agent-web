package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer;

import java.io.Serializable;

public class SelfCustomerGetInfoRequest extends SelfCustomerGetInfoRequestType implements Serializable {

  private static final long serialVersionUID = 1L;

  public SelfCustomerGetInfoRequest(Long customerId) {
    this.customerId = customerId;
  }

  public SelfCustomerGetInfoRequest() {

  }
}
