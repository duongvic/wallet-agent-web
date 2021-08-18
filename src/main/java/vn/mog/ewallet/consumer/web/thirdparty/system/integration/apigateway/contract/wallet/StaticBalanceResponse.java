package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet;

import java.util.HashMap;
import java.util.Map;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.beans.CompareFaAndWalletBalance;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.beans.StaticBalance;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class StaticBalanceResponse extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;
  protected Map<String, StaticBalance> statsBalances;

  protected CompareFaAndWalletBalance verifyBalance;

  public Map<String, StaticBalance> getStatsBalances() {
    if (statsBalances == null) {
      statsBalances = new HashMap<>();
    }
    return statsBalances;
  }

  public void setStatsBalances(Map<String, StaticBalance> statsBalances) {
    this.statsBalances = statsBalances;
  }

  public CompareFaAndWalletBalance getVerifyBalance() {
    if (verifyBalance == null) {
      verifyBalance = new CompareFaAndWalletBalance();
    }
    return verifyBalance;
  }

  public void setVerifyBalance(CompareFaAndWalletBalance verifyBalance) {
    this.verifyBalance = verifyBalance;
  }

}
