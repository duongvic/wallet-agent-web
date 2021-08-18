package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type;

public enum ActiveType {
  ON(1), OFF(0);
  public int code;

  private ActiveType(int code) {
    this.code = code;
  }

}
