package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow;

public class OrderFlowSubmitProcessRequest extends OrderFlowSubmitProcessRequestType {

  private static final long serialVersionUID = 1L;

  public OrderFlowSubmitProcessRequest(Long orderId, String remark) {
    this.orderId = orderId;
    this.remark = remark;
  }

}
