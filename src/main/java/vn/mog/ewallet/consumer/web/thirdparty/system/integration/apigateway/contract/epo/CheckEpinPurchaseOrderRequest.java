package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo;

import java.util.List;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.beans.EpinPurchaseOrderDetail;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class CheckEpinPurchaseOrderRequest extends MobiliserRequestType {
  private static final long serialVersionUID = 1L;

  private List<EpinPurchaseOrderDetail> purchaseOrderDetails;

  public List<EpinPurchaseOrderDetail> getPurchaseOrderDetails() {
    return purchaseOrderDetails;
  }

  public void setPurchaseOrderDetails(List<EpinPurchaseOrderDetail> purchaseOrderDetails) {
    this.purchaseOrderDetails = purchaseOrderDetails;
  }
}
