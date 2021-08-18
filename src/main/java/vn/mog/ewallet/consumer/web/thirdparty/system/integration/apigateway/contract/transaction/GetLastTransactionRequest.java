package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class GetLastTransactionRequest extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  private Long customerId;

  public GetLastTransactionRequest(Long customerId) {
    this.customerId = customerId;
  }

  public GetLastTransactionRequest() {
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

}
