package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class GetEpinPurchaseOrderAttachmentRequest extends MobiliserRequestType {
  private static final long serialVersionUID = 1L;

  private Long purchaseOrderId;
  private Action action; // Get file or get Password

  public GetEpinPurchaseOrderAttachmentRequest() {}

  public GetEpinPurchaseOrderAttachmentRequest(Long purchaseOrderId, Action action) {
    this.purchaseOrderId = purchaseOrderId;
    this.action = action;
  }

  public Long getPurchaseOrderId() {
    return purchaseOrderId;
  }

  public void setPurchaseOrderId(Long purchaseOrderId) {
    this.purchaseOrderId = purchaseOrderId;
  }

  public Action getAction() {
    return action;
  }

  public void setAction(Action action) {
    this.action = action;
  }

  public enum Action {
    GET_FILE, GET_PASSWORD
  }
}
