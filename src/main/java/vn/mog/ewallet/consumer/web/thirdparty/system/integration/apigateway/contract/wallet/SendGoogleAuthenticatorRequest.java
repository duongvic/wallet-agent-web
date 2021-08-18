package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet;

import lombok.Data;
import vn.mog.framework.contract.base.MobiliserRequestType;

@Data
public class SendGoogleAuthenticatorRequest extends MobiliserRequestType {

  private Long customerId;
}
