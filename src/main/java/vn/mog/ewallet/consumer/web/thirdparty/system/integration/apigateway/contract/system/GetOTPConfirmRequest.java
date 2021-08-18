package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class GetOTPConfirmRequest extends MobiliserRequestType{

  private static final long serialVersionUID = 1L;
  protected Long orderId;

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public GetOTPConfirmRequest(Long orderId) {
    this.orderId = orderId;
  }

  public GetOTPConfirmRequest() {

  }
}
