package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet;


import lombok.Data;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.beans.WalletUser;
import vn.mog.framework.contract.base.MobiliserResponseType;

@Data
public class WalletCashOutInfoGetResponse extends MobiliserResponseType {

  private Long orderId;
  private WalletUser sender;
  private WalletUser receiver;
}
