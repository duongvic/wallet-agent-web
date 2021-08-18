package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo;

import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.beans.EpinPurchaseOrder;
import vn.mog.framework.contract.base.MobiliserRequestType;

@SuppressWarnings("serial")
public class UpdateEpinPurchaseOrderRequest extends
    MobiliserRequestType {

  private EpinPurchaseOrder purchaseOrder;
  private Action action;

  public UpdateEpinPurchaseOrderRequest(EpinPurchaseOrder purchaseOrder) {
    this.purchaseOrder = purchaseOrder;
  }

  public EpinPurchaseOrder getPurchaseOrder() {
    return purchaseOrder;
  }

  public void setPurchaseOrder(EpinPurchaseOrder purchaseOrder) {
    this.purchaseOrder = purchaseOrder;
  }

  public UpdateEpinPurchaseOrderRequest.Action getAction() {
    return action;
  }

  public void setAction(UpdateEpinPurchaseOrderRequest.Action action) {
    this.action = action;
  }

  public enum Action {
    SAVE, NEXT
  }
}
