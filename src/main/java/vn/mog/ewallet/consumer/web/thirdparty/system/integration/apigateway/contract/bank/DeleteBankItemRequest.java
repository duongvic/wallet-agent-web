package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class DeleteBankItemRequest extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  private String itemType;

  private Long bankItemId;

  public Long getBankItemId() {
    return bankItemId;
  }

  public String getItemType() {
    return itemType;
  }

  public void setItemType(String itemType) {
    this.itemType = itemType;
  }

  public void setBankItemId(Long bankItemId) {
    this.bankItemId = bankItemId;
  }
}
