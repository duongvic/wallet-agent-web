package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet;

import java.util.List;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.beans.FundOrder;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.beans.FundOrderAttachment;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class CreateFundOrderRequest extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  public FundOrder getOrder() {
    return order;
  }

  public void setOrder(
      FundOrder order) {
    this.order = order;
  }

  private FundOrder order;


  public CreateFundOrderRequest(FundOrder fundOutOrder, Long customerId) {

  }

  public CreateFundOrderRequest(FundOrder fundOrder) {

  }

  public CreateFundOrderRequest(FundOrder fundOutOrder, Long customerId, List<FundOrderAttachment> attachments) {

  }
}
