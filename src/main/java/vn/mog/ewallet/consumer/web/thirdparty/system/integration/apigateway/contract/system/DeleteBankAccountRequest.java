package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class DeleteBankAccountRequest extends MobiliserRequestType {

  public Long getBankAccountId() {
    return bankAccountId;
  }

  public void setBankAccountId(Long bankAccountId) {
    this.bankAccountId = bankAccountId;
  }

  private Long bankAccountId;
}
