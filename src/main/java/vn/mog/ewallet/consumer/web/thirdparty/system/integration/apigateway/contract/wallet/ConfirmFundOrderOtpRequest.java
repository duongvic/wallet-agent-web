package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet;

import java.io.Serializable;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class ConfirmFundOrderOtpRequest extends MobiliserRequestType implements Serializable {

  private String serviceTypeId;//"":""FUND_OUT"",

  private Integer orderId;//"":15028,

  private Integer otp;//"":123456

  public String getServiceTypeId() {
    return serviceTypeId;
  }

  public void setServiceTypeId(String serviceTypeId) {
    this.serviceTypeId = serviceTypeId;
  }

  public Integer getOrderId() {
    return orderId;
  }

  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }

  public Integer getOtp() {
    return otp;
  }

  public void setOtp(Integer otp) {
    this.otp = otp;
  }
}
