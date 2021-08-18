package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank;

import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.bean.CustomerBankItem;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class CreateBankItemRequest extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  private String itemType;

  private CustomerBankItem bankItem;

  public String getItemType() {
    return itemType;
  }

  public void setItemType(String itemType) {
    this.itemType = itemType;
  }

  public CustomerBankItem getBankItem() {
    return bankItem;
  }

  public void setBankItem(CustomerBankItem bankItem) {
    this.bankItem = bankItem;
  }
}
