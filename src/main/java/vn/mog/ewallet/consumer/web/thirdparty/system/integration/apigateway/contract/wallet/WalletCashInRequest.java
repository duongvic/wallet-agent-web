package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet;

import lombok.Data;
import vn.mog.framework.contract.base.MobiliserRequestType;

@Data
public class WalletCashInRequest extends MobiliserRequestType {

  private Long orderId;
  private String keyOtpFee; /* Key otp vt server trả về client trong bản tin OTP_CASH_IN_VTPAY
                               (trường hợp nạp cho chính mình sẽ có value*/
  private String senderOtp; /* Otp khách hàng cung cấp
                      (trường hợp nạp cho chính mình sẽ có value*/
  private String otp;
}
