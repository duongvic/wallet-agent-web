package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.kyc;

import vn.mog.framework.contract.base.MobiliserResponseType;

public class VerifyKycRequestChangeResponse extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  private Long requestId;

  public Long getRequestId() {
    return requestId;
  }

  public void setRequestId(Long requestId) {
    this.requestId = requestId;
  }
}
