package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info;

import java.util.List;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info.bean.Customer;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info.bean.CustomerExtraInfo;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.bean.Identity;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetConsumerAccountResponse extends MobiliserResponseType {
  private Customer customer;
  private List<Identity> identities;
  private CustomerExtraInfo extraInfo;


  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public CustomerExtraInfo getExtraInfo() {
    return extraInfo;
  }

  public void setExtraInfo(CustomerExtraInfo extraInfo) {
    this.extraInfo = extraInfo;
  }

  public List<Identity> getIdentities() {
    return identities;
  }

  public void setIdentities(
      List<Identity> identities) {
    this.identities = identities;
  }
}
