package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet;

import com.fasterxml.jackson.annotation.JsonProperty;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class FundInRequest extends MobiliserRequestType{

  private static final long serialVersionUID = 1L;

  @JsonProperty("request_id")
  private String requestId; // Mã gửi yêu cầu, duy nhất (M)

  @JsonProperty("order_id")
  private Long fundOrderId; // Mã yêu cầu nạp tiền (M)

  private String remark; // Ghi chú (O)

  private String otp; // One time password (O)

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public Long getFundOrderId() {
    return fundOrderId;
  }

  public void setFundOrderId(Long fundOrderId) {
    this.fundOrderId = fundOrderId;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getOtp() {
    return otp;
  }

  public void setOtp(String otp) {
    this.otp = otp;
  }

  public FundInRequest(String requestId, Long fundOrderId, String otp) {
    this.requestId = requestId;
    this.fundOrderId = fundOrderId;
    this.otp = otp;
  }
}
