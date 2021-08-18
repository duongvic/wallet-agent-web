package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class GetConfirmOtpOrderRequest extends MobiliserRequestType{

  private static final long serialVersionUID = 1L;

  private String serviceTypeId;//"":""FUND_OUT"",

  private Integer orderId;//15027

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
}
