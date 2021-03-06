package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo;

import java.util.HashMap;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.bean.PurchaseOrderItem;
import vn.mog.framework.contract.base.MobiliserRequestType;

@SuppressWarnings("serial")
public class UpdatePurchaseOrderRequest extends MobiliserRequestType {

  protected PurchaseOrderItem purchaseOrder;
  protected boolean includePODetail;
  protected HashMap<String, byte[]> files;

  public boolean getIncludePODetail() {
    return includePODetail;
  }

  public boolean isIncludePODetail() {
    return includePODetail;
  }

  public void setIncludePODetail(boolean includePODetail) {
    this.includePODetail = includePODetail;
  }

  public PurchaseOrderItem getPurchaseOrder() {
    return purchaseOrder;
  }

  public void setPurchaseOrder(PurchaseOrderItem purchaseOrder) {
    this.purchaseOrder = purchaseOrder;
  }

  public HashMap<String, byte[]> getFiles() {
    return files;
  }

  public void setFiles(HashMap<String, byte[]> files) {
    this.files = files;
  }
}
