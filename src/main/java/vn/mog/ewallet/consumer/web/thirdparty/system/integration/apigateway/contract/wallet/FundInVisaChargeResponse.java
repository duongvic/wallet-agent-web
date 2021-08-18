package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet;


import vn.mog.framework.contract.base.MobiliserResponseType;


public class FundInVisaChargeResponse extends MobiliserResponseType {

  /**
   * " pay_url: url redirect user đến cổng thanh toán
   *  fund_order_id: mã order tương ứng
   *
   *  signature: HMacSHA256(data,key). data = fund_order_id + ""|"" + ""pay_url"" ~ ""50925|https://api.pay.zo-ta.com/bank-charging-test/tmv-process.jsp?token=u1cldsUpr2uDrB9JRit8hnzqTqQoieq2NyBc4yVHhWA%3D""
   *
   * KEY: d21d69363e0711e8b4670ed5f89f718b"
   * */

  private String payUrl;

  private String fundOrderId;

  private String signature;

  public String getPayUrl() {
    return payUrl;
  }

  public void setPayUrl(String payUrl) {
    this.payUrl = payUrl;
  }

  public String getFundOrderId() {
    return fundOrderId;
  }

  public void setFundOrderId(String fundOrderId) {
    this.fundOrderId = fundOrderId;
  }

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }
}
