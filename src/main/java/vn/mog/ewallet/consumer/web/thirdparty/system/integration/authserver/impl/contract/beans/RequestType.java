package vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.beans;

import java.io.Serializable;

public class RequestType implements Serializable {

  private static final long serialVersionUID = 1L;

  protected Long callerId;

  protected String action;

  public Long getCallerId() {
    return callerId;
  }

  public void setCallerId(Long callerId) {
    this.callerId = callerId;
  }

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }
}