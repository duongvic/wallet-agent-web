package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type;

public enum AddressType {
  POSTAL_AND_DELIVERY(0), DELIVERY_ONLY(1), OUTLET_ADDRESS(2), THIRD_PARTY_ADDRESS(3),
  SP_TECHNICAL_CONTACT(4), SP_OPERATIONS_CONTACT(5), BUSINESS_CONTACT(6), LIVING_CONTACT(7),
  RESIDENCE_ADDRESS(8);

  private Integer code;

  AddressType(Integer code) {
    this.code = code;
  }

  public Integer getCode() {
    return code;
  }
}
