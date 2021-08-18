package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo;

import vn.mog.framework.contract.base.MobiliserResponseType;

@SuppressWarnings("serial")
public class UpdateEpinPurchaseOrderResponse extends MobiliserResponseType {

  private Long purchaseOrderId;
  private String purchaseOrderCode;

  public UpdateEpinPurchaseOrderResponse() {
  }

  public Long getPoId() {
    return purchaseOrderId;
  }

  public void setPoId(Long poId) {
    this.purchaseOrderId = poId;
  }

  public String getPoCode() {
    return purchaseOrderCode;
  }

  public void setPoCode(String poCode) {
    this.purchaseOrderCode = poCode;
  }
}
