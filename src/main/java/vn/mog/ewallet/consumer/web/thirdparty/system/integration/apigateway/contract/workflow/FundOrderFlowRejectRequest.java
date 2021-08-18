package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow;

import java.io.Serializable;

public class FundOrderFlowRejectRequest extends FundOrderFlowRejectRequestType implements
    Serializable {

  private static final long serialVersionUID = 1L;

  public FundOrderFlowRejectRequest(Long fundOrderId, String remark) {
    this.fundOrderId = fundOrderId;
    this.remark = remark;
  }
}
