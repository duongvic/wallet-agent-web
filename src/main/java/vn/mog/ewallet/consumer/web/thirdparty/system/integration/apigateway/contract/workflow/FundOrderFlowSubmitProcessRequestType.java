package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class FundOrderFlowSubmitProcessRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  protected Long fundOrderId;
  protected String remark;


  public Long getFundOrderId() {
    return fundOrderId;
  }

  public void setFundOrderId(Long orderId) {
    this.fundOrderId = orderId;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
}
