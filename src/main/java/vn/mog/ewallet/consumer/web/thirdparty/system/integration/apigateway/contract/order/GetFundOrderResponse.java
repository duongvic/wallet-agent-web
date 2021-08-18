package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order;

import java.util.ArrayList;
import java.util.List;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order.bean.FundOrder;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.beans.FundOrderAttachment;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetFundOrderResponse extends MobiliserResponseType {
  private static final long serialVersionUID = 1L;

  private FundOrder order;

  private List<FundOrderAttachment> attachments;

  private Long customerCurrentBalance;

  private String bankCodeSOF;

  public FundOrder getOrder() {
    return order;
  }

  public void setOrder(FundOrder order) {
    this.order = order;
  }

  public List<FundOrderAttachment> getAttachments() {
    if (attachments == null)
      attachments = new ArrayList<FundOrderAttachment>();
    return attachments;
  }

  public void setAttachments(List<FundOrderAttachment> attachments) {
    this.attachments = attachments;
  }

  public Long getCustomerCurrentBalance() {
    return customerCurrentBalance;
  }

  public void setCustomerCurrentBalance(Long customerCurrentBalance) {
    this.customerCurrentBalance = customerCurrentBalance;
  }

  public String getBankCodeSOF() {
    return bankCodeSOF;
  }

  public void setBankCodeSOF(String bankCodeSOF) {
    this.bankCodeSOF = bankCodeSOF;
  }

}
