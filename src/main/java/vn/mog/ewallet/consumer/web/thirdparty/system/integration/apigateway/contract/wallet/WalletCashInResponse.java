package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet;

import lombok.Data;
import vn.mog.framework.contract.base.MobiliserResponseType;

@Data
public class WalletCashInResponse extends MobiliserResponseType {

  private Long transactionId;
}
