package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order;

import java.io.Serializable;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class UpdateFundOrderResponse extends MobiliserResponseType {
  private static final long serialVersionUID = 1L;

  private Long orderId;

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }
}
