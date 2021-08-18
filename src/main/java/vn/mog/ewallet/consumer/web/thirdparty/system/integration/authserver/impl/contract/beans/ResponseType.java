package vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.beans;

import java.io.Serializable;

public class ResponseType implements Serializable {

  private static final long serialVersionUID = 1L;

  protected Status status;

  public Status getStatus() {
    return this.status;
  }

  public void setStatus(Status value) {
    this.status = value;
  }

  public static class Status implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String value;

    protected int code;

    public Status() {

    }

    public Status(int code) {
      this.code = code;
    }

    public Status(int code, String value) {
      this.code = code;
      this.value = value;
    }


    public String getValue() {
      return this.value;
    }

    public void setValue(String value) {
      this.value = value;
    }

    public int getCode() {
      return this.code;
    }

    public void setCode(int value) {
      this.code = value;
    }
  }
}