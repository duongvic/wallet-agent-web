package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow;

import java.io.Serializable;

public class FundOrderFlowStartProcessRequest extends FundOrderFlowStartProcessRequestType
    implements Serializable {

  private static final long serialVersionUID = 1L;


  public FundOrderFlowStartProcessRequest(Long fundOrderId, String remark) {
    this.fundOrderId = fundOrderId;
    this.remark = remark;
  }

}
