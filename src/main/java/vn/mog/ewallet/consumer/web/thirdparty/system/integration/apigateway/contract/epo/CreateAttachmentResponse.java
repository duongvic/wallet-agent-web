package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo;

import java.util.List;
import vn.mog.framework.contract.base.MobiliserResponseType;

@SuppressWarnings("serial")
public class CreateAttachmentResponse extends MobiliserResponseType {

  protected List<Long> attachmentIds;

  public List<Long> getAttachmentIds() {
    return attachmentIds;
  }

  public void setAttachmentIds(List<Long> attachmentIds) {
    this.attachmentIds = attachmentIds;
  }
}
