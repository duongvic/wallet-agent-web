package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo;

import java.io.Serializable;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class PurchaseOrderFlowRejectRequest extends MobiliserRequestType{

  protected Long purchaseOrderId;
  protected String info;

  public Long getPurchaseOrderId() {
    return purchaseOrderId;
  }

  public void setPurchaseOrderId(Long purchaseOrderId) {
    this.purchaseOrderId = purchaseOrderId;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public PurchaseOrderFlowRejectRequest(Long purchaseOrderId, String info) {
    this.purchaseOrderId = purchaseOrderId;
    this.info = info;
  }

  public PurchaseOrderFlowRejectRequest() {

  }
}
