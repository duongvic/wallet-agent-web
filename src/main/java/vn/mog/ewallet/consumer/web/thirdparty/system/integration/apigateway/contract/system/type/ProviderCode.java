package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type;

import java.util.Arrays;
import java.util.List;

public enum ProviderCode {
  /* SANDBOX PROVIDER: For Dev Env */
  ONEPAY,
  VTC,
  VTC_EPIN,
  VTC_PTU,
  VNPAY,
  VINATOPUP,
  EPAY,
  SOFCASH_SERVICE,
  CARD_STORE,
  CARD_STORE_N02,
  OFFLINE_CARD_STORE,
  FIVIPAY,
  TRUE_MONEY,
  LOGICPAY,
  SANDBOX,
  NAPAS,
  FECREDIT,
  HOMECREDIT,
  ONEWORLD,
  MY_BILL,
  WHYPAY,
  IPPAY,
  IOMEDIA_EPIN,
  IOMEDIA_PTU,
  BILL_EPAY,
  BILL_SENPAY,
  BILL_VIETTELPAY;

  public static List<ProviderCode> PAYMENT_PROVIDER_CODES = Arrays.asList(ProviderCode.values());

  public static ProviderCode getAvailableProviderCode(String label) {
    for (ProviderCode item : ProviderCode.values()) {
      if (item.name().equals(label.trim())) {
        return item;
      }
    }
    return null;
  }
}
