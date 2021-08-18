package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow;

import java.io.Serializable;

public class FundOrderFlowApproveRequest extends FundOrderFlowApproveRequestType implements
    Serializable {

  private static final long serialVersionUID = 1L;


  public FundOrderFlowApproveRequest(Long fundOrderId) {
    this.fundOrderId = fundOrderId;
  }

  public FundOrderFlowApproveRequest(Long fundOrderId, String remark) {
    this.fundOrderId = fundOrderId;
    this.remark = remark;
  }
}
