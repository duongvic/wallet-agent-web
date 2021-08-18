package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet;

import lombok.Data;
import vn.mog.framework.contract.base.MobiliserRequestType;

@Data
public class WalletCashInOTPGetRequest extends MobiliserRequestType {

  private String senderMsisdn;
  private String senderName;
  private String receiverMsisdn;
  private long amount;
  private String transContent;
}
