package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo;

public class ExportEpinPurchaseOrderFormRequest extends ExportEpinPurchaseOrderFormRequestType {
  private static final long serialVersionUID = 1L;

  private Long purchaseOrderId;

  public ExportEpinPurchaseOrderFormRequest() {}

  public ExportEpinPurchaseOrderFormRequest(Long purchaseOrderId) {
    this.purchaseOrderId = purchaseOrderId;
  }

  public Long getPurchaseOrderId() {
    return purchaseOrderId;
  }

  public void setPurchaseOrderId(Long purchaseOrderId) {
    this.purchaseOrderId = purchaseOrderId;
  }
}
