package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.bean.CustomerBankItem;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetBankItemByCustomerResponse extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  private List<CustomerBankItem> bankItems;

  public List<CustomerBankItem> getBankItems() {
    return bankItems;
  }

  public void setBankItems(List<CustomerBankItem> bankItems) {
    this.bankItems = bankItems;
  }
}
