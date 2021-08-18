package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system;

import java.io.Serializable;

import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.BankAccount;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class CreateBankAccountRequest extends MobiliserRequestType implements Serializable {

  private BankAccount bankAccount;

  public BankAccount getBankAccount() {
    return bankAccount;
  }

  public void setBankAccount(BankAccount bankAccount) {
    this.bankAccount = bankAccount;
  }

  public CreateBankAccountRequest(BankAccount bankAccount) {
    this.bankAccount = bankAccount;
  }
}
