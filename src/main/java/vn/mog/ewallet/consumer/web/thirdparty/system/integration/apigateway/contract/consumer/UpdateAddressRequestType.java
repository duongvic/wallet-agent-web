package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer;

import java.io.Serializable;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.bean.Address;
import vn.mog.framework.contract.base.MobiliserRequestType;


public class UpdateAddressRequestType extends MobiliserRequestType implements Serializable {

  private static final long serialVersionUID = 1L;

  protected Address address;

  public Address getAddress() {
    return this.address;
  }

  public void setAddress(Address value) {
    this.address = value;
  }

}
