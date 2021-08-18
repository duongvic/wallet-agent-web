package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow;

import java.io.Serializable;

public class OrderFlowStartProcessRequest extends OrderFlowStartProcessRequestType
    implements Serializable {

  private static final long serialVersionUID = 1L;

  public OrderFlowStartProcessRequest(Long orderId, String remark) {
    this.orderId = orderId;
    this.remark = remark;
  }

}
