package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system;

import java.util.List;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.Bank;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class FindBankResponse extends MobiliserResponseType{

  private static final long serialVersionUID = 1L;

  List<Bank> banks;

  public List<Bank> getBanks() {
    return banks;
  }

  public void setBanks(List<Bank> banks) {
    this.banks = banks;
  }
}
