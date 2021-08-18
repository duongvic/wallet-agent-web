package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet;

import java.util.List;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.bean.CustomerBankDirect;
import vn.mog.framework.contract.base.MobiliserResponseType;


public class FindCustomerBankDirectResponse extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  private List<CustomerBankDirect> bankDirects;

  public List<CustomerBankDirect> getBankDirects() {
    return bankDirects;
  }

  public void setBankDirects(List<CustomerBankDirect> bankDirects) {
    this.bankDirects = bankDirects;
  }

}
