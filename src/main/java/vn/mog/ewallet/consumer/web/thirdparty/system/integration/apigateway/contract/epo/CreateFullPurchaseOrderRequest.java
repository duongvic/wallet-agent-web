package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo;

import java.util.ArrayList;
import java.util.List;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.bean.Attachment;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.bean.PurchaseOrderItem;
import vn.mog.framework.contract.base.MobiliserRequestType;


@SuppressWarnings("serial")
public class CreateFullPurchaseOrderRequest extends MobiliserRequestType {

  protected PurchaseOrderItem purchaseOrder;
  protected List<Attachment> attachments;



  public PurchaseOrderItem getPurchaseOrder() {
    return purchaseOrder;
  }

  public void setPurchaseOrder(PurchaseOrderItem purchaseOrder) {
    this.purchaseOrder = purchaseOrder;
  }

  public List<Attachment> getAttachments() {
    if (attachments == null) {
      attachments = new ArrayList<Attachment>();
    }
    return attachments;
  }

  public void setAttachments(List<Attachment> attachments) {
    this.attachments = attachments;
  }

  public CreateFullPurchaseOrderRequest(PurchaseOrderItem purchaseOrder) {
    this.purchaseOrder = purchaseOrder;
  }

  public CreateFullPurchaseOrderRequest() {

  }
}
