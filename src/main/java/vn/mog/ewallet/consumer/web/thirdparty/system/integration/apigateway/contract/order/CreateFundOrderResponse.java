package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order;

import vn.mog.framework.contract.base.MobiliserResponseType;

public class CreateFundOrderResponse extends MobiliserResponseType {
  private static final long serialVersionUID = 1L;

  private Long orderId;

  private String syntax ;

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public String getSyntax() {
    return syntax;
  }

  public void setSyntax(String syntax) {
    this.syntax = syntax;
  }
}
