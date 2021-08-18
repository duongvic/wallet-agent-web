package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo;

import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.beans.EpinPurchaseOrderAttachment;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetEpinPurchaseOrderAttachmentResponse extends MobiliserResponseType{

  private static final long serialVersionUID = 1L;

  private EpinPurchaseOrderAttachment attachment;

  public EpinPurchaseOrderAttachment getAttachment() {
    return attachment;
  }

  public void setAttachment(EpinPurchaseOrderAttachment attachment) {
    this.attachment = attachment;
  }

}