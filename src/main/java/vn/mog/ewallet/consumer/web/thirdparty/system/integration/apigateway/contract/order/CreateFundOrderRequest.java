package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order;

import java.util.ArrayList;
import java.util.List;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order.bean.FundOrder;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.beans.FundOrderAttachment;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class CreateFundOrderRequest extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  private FundOrder order;

  private List<FundOrderAttachment> attachments;

  public FundOrder getOrder() {
    return order;
  }

  public void setOrder(FundOrder order) {
    this.order = order;
  }

  public List<FundOrderAttachment> getAttachments() {
    if (attachments == null) {
      attachments = new ArrayList<>();
    }
    return attachments;
  }

  public void setAttachments(List<FundOrderAttachment> attachments) {
    this.attachments = attachments;
  }
}
