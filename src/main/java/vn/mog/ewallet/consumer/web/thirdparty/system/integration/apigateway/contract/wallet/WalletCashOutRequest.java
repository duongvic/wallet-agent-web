package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet;

import lombok.Data;
import vn.mog.framework.contract.base.MobiliserRequestType;

@Data
public class WalletCashOutRequest extends MobiliserRequestType {

  private Long orderId;
  private String otp;
}
