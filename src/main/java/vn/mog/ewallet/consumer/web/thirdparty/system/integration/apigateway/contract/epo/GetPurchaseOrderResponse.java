package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo;

import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.bean.PurchaseOrderItem;
import vn.mog.framework.contract.base.MobiliserResponseType;

@SuppressWarnings("serial")
public class GetPurchaseOrderResponse extends MobiliserResponseType {

  protected PurchaseOrderItem purchaseOrder;

  public PurchaseOrderItem getPurchaseOrder() {
    return purchaseOrder;
  }

  public void setPurchaseOrder(PurchaseOrderItem purchaseOrder) {
    this.purchaseOrder = purchaseOrder;
  }
}
