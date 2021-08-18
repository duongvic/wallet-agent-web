package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.BankProfile;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class FindBankProfileResponse extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  private List<BankProfile> bankProfiles;

  public List<BankProfile> getBankProfiles() {
    if (bankProfiles == null) {
      return Collections.emptyList();
    }
    return bankProfiles;
  }

  public void setBankProfiles(List<BankProfile> bankProfiles) {
    this.bankProfiles = bankProfiles;
  }
}
