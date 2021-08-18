package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo;

import java.util.List;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.bean.Attachment;
import vn.mog.framework.contract.base.MobiliserRequestType;


@SuppressWarnings("serial")
public class CreateAttachmentRequest extends MobiliserRequestType {

  protected List<Attachment> attachment;
  protected String note;



  public List<Attachment> getAttachment() {
    return attachment;
  }

  public void setAttachment(List<Attachment> attachment) {
    this.attachment = attachment;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public CreateAttachmentRequest(List<Attachment> attachment, String note) {
    this.attachment = attachment;
    this.note = note;
  }

  public CreateAttachmentRequest() {

  }
}
