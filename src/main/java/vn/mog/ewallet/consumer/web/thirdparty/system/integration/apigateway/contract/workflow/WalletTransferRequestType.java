package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow;

import java.io.Serializable;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class WalletTransferRequestType extends MobiliserRequestType implements Serializable {

  private static final long serialVersionUID = 1L;

  protected Long orderId;
  protected String otp;


  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public String getOtp() {
    return otp;
  }

  public void setOtp(String otp) {
    this.otp = otp;
  }
}
