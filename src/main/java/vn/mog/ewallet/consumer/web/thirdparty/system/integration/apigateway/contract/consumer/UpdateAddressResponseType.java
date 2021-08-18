package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer;

import java.io.Serializable;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class UpdateAddressResponseType extends MobiliserResponseType implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long addressId;

  public Long getAddressId() {
    return addressId;
  }

  public void setAddressId(Long addressId) {
    this.addressId = addressId;
  }
}
