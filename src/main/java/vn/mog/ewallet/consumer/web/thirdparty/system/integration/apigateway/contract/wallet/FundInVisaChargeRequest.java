package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class FundInVisaChargeRequest extends MobiliserRequestType {

  private String resultUrl;

  private String signature;//"":""587d3053fd9ecb610b9229491726d0a7a86600ab0898a4029fea710306831b92""

  private Long amount;

  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public String getResultUrl() {
    return resultUrl;
  }

  public void setResultUrl(String resultUrl) {
    this.resultUrl = resultUrl;
  }

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }



}
