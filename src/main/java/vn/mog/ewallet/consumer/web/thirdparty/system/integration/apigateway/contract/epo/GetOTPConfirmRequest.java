package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo;

import vn.mog.framework.contract.base.MobiliserRequestType;

@SuppressWarnings("serial")
public class GetOTPConfirmRequest extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  private Long purchaseOrderId;

  public GetOTPConfirmRequest() {
  }

  public GetOTPConfirmRequest(Long purchaseOrderId) {
    this.purchaseOrderId = purchaseOrderId;
  }

  public Long getPurchaseOrderId() {
    return purchaseOrderId;
  }

  public void setPurchaseOrderId(Long purchaseOrderId) {
    this.purchaseOrderId = purchaseOrderId;
  }
}
