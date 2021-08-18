package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo;

import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.beans.EpinPurchaseOrder;
import vn.mog.framework.contract.base.MobiliserResponseType;

@SuppressWarnings("serial")
public class GetEpinPurchaseOrderResponse extends MobiliserResponseType {

  private EpinPurchaseOrder purchaseOrder;

  public GetEpinPurchaseOrderResponse() {
  }

  public EpinPurchaseOrder getPurchaseOrder() {
    return purchaseOrder;
  }

  public void setPurchaseOrder(EpinPurchaseOrder purchaseOrder) {
    this.purchaseOrder = purchaseOrder;
  }
}
