package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet;

import lombok.Data;
import vn.mog.framework.contract.base.MobiliserRequestType;

@Data
public class WalletCashOutInfoGetRequest extends MobiliserRequestType {

  private String receiptCode;
  private String receiverMsisdn;
  private String receiverName;
  private long amount;
}
