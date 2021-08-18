package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.kyc;


import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.bean.CustomerVerifyKycRequest;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class VerifyKycRequestChangeRequest extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;
  
  private CustomerVerifyKycRequest customerVerifyRequest;

  public CustomerVerifyKycRequest getCustomerVerifyRequest() {
    return customerVerifyRequest;
  }

  public void setCustomerVerifyRequest(CustomerVerifyKycRequest customerVerifyRequest) {
    this.customerVerifyRequest = customerVerifyRequest;
  }
}
