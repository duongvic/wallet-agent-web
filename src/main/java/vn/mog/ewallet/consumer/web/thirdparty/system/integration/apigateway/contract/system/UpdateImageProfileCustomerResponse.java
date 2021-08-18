package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system;

import vn.mog.framework.contract.base.MobiliserResponseType;

public class UpdateImageProfileCustomerResponse extends MobiliserResponseType {

  private String keyS3;

  public String getKeyS3() {
    return keyS3;
  }

  public void setKeyS3(String keyS3) {
    this.keyS3 = keyS3;
  }
}
