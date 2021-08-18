package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo;

import java.io.Serializable;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class PurchaseOrderFlowRejectResponse extends MobiliserResponseType{

  protected Long purchaseOrderId;

  public Long getPurchaseOrderId() {
    return purchaseOrderId;
  }

  public void setPurchaseOrderId(Long purchaseOrderId) {
    this.purchaseOrderId = purchaseOrderId;
  }
}
