package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order;

import java.util.List;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order.bean.FundOrder;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.beans.FundOrderAttachment;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class UpdateFundOrderRequest extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  private FundOrder order;

  private List<FundOrderAttachment> attachments;

  private Long customerId;// FUCK

  private String bankCodeSOF;

  public FundOrder getOrder() {
    return order;
  }

  public void setOrder(FundOrder order) {
    this.order = order;
  }

  public List<FundOrderAttachment> getAttachments() {
    return attachments;
  }

  public void setAttachments(List<FundOrderAttachment> attachments) {
    this.attachments = attachments;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public String getBankCodeSOF() {
    return bankCodeSOF;
  }

  public void setBankCodeSOF(String bankCodeSOF) {
    this.bankCodeSOF = bankCodeSOF;
  }
}
