package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class GetFundOrderRequest extends MobiliserRequestType{

  private static final long serialVersionUID = 1L;

  protected Long fundOrderId;

  public Long getFundOrderId() {
    return fundOrderId;
  }

  public void setFundOrderId(Long fundOrderId) {
    this.fundOrderId = fundOrderId;
  }

  public GetFundOrderRequest(Long fundOrderId) {
    this.fundOrderId = fundOrderId;
  }

  public GetFundOrderRequest() {

  }
}
