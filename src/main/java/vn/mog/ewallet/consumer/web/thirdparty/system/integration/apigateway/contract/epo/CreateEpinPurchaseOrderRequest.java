package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo;

import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.beans.EpinPurchaseOrder;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class CreateEpinPurchaseOrderRequest extends MobiliserRequestType {

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private EpinPurchaseOrder purchaseOrder;
  private Action action;

  public CreateEpinPurchaseOrderRequest() {
  }

  public CreateEpinPurchaseOrderRequest(EpinPurchaseOrder purchaseOrder) {
    this.purchaseOrder = purchaseOrder;
  }

  public EpinPurchaseOrder getPurchaseOrder() {
    return purchaseOrder;
  }

  public void setPurchaseOrder(EpinPurchaseOrder purchaseOrder) {
    this.purchaseOrder = purchaseOrder;
  }

  public Action getAction() {
    return action;
  }

  public void setAction(Action action) {
    this.action = action;
  }

  public enum Action {
    SAVE, NEXT
  }
}
