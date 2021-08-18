package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system;

import java.util.Date;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class UpdateImageProfileCustomerRequest extends MobiliserRequestType {

  protected String name;
  protected String contentType;
  protected String content;
  protected Date created;
  protected int status;

  public UpdateImageProfileCustomerRequest() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }
}
