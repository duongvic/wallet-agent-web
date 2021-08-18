package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet;

import vn.mog.framework.contract.base.MobiliserResponseType;

public class CreateFundOrderResponse extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  protected Long fundOrderId;

  public Long getFundOrderId() {
    return fundOrderId;
  }

  public void setFundOrderId(Long fundOrderId) {
    this.fundOrderId = fundOrderId;
  }
}
