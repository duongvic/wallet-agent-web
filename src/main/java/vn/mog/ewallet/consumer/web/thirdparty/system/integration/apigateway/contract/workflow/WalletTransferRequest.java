package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow;

import java.io.Serializable;

public class WalletTransferRequest extends WalletTransferRequestType implements Serializable {

  private static final long serialVersionUID = 1L;

  public WalletTransferRequest(Long orderId, String otp) {
    this.orderId = orderId;
    this.otp = otp;
  }

  public WalletTransferRequest() {

  }
}
