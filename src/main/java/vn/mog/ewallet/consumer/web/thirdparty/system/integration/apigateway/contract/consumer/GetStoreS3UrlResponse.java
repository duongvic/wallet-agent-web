package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer;

import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.Attachment;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetStoreS3UrlResponse extends MobiliserResponseType {

  private String s3Url;
  private Boolean usedS3;
  private Attachment attachment;
  public GetStoreS3UrlResponse() {

  }
  public String getS3Url() {
    return s3Url;
  }

  public void setS3Url(String s3Url) {
    this.s3Url = s3Url;
  }

  public Boolean getUsedS3() {
    return usedS3;
  }

  public void setUsedS3(Boolean usedS3) {
    this.usedS3 = usedS3;
  }

  public Attachment getAttachment() {
    return attachment;
  }

  public void setAttachment(Attachment attachment) {
    this.attachment = attachment;
  }

  public GetStoreS3UrlResponse(String s3Url, Boolean usedS3, Attachment attachment) {
    this.s3Url = s3Url;
    this.usedS3 = usedS3;
    this.attachment = attachment;
  }
}
