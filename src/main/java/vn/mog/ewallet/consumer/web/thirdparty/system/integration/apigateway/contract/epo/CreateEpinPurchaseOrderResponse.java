package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo;

import vn.mog.framework.contract.base.MobiliserResponseType;


public class CreateEpinPurchaseOrderResponse extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  protected Long purchaseOrderId;
  protected String purchaseOrderCode;

  public Long getPurchaseOrderId() {
    return purchaseOrderId;
  }

  public void setPurchaserOrderId(Long purchaseOrderId) {
    this.purchaseOrderId = purchaseOrderId;
  }

  public String getPurchaserOrderCode() {
    return purchaseOrderCode;
  }

  public void setPurchaseOrderCode(String purchaseOrderCode) {
    this.purchaseOrderCode = purchaseOrderCode;
  }
}
