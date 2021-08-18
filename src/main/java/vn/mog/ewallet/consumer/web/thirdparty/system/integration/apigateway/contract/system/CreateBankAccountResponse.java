package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system;

import java.io.Serializable;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class CreateBankAccountResponse extends MobiliserResponseType implements Serializable {

  private Long bankAccountId;

  public Long getBankAccountId() {
    return bankAccountId;
  }

  public void setBankAccountId(Long bankAccountId) {
    this.bankAccountId = bankAccountId;
  }


}
