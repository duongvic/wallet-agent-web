package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow;

import java.io.Serializable;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class FundOrderFlowStartProcessRequestType extends MobiliserResponseType implements
    Serializable {

  private static final long serialVersionUID = 1L;

  protected Long fundOrderId;
  protected String remark;


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
}
