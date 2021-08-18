package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet;

import lombok.Data;
import vn.mog.framework.contract.base.MobiliserResponseType;

@Data
public class WalletCashInFeeGetResponse extends MobiliserResponseType {

  private Long orderId;
  private String receiverName;
  private Long transFee;
}
