package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow;

public class FundOrderFlowSubmitProcessRequest extends FundOrderFlowSubmitProcessRequestType {

  private static final long serialVersionUID = 1L;

  public FundOrderFlowSubmitProcessRequest(Long fundOrderId) {
    this.fundOrderId = fundOrderId;
  }
}
