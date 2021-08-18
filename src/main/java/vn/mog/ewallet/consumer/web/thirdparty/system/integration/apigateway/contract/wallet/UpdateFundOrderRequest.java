package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.beans.FundOrder;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.beans.FundOrderAttachment;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class UpdateFundOrderRequest extends MobiliserRequestType{

  private static final long serialVersionUID = 1L;

  protected FundOrder fundOrder;
  protected List<FundOrderAttachment> attachments;
  protected Long customerId;
  protected String bankCodeSOF;

  public FundOrder getFundOrder() {
    return fundOrder;
  }

  public void setFundOrder(FundOrder fundOrder) {
    this.fundOrder = fundOrder;
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

  public UpdateFundOrderRequest() {
  }

  public UpdateFundOrderRequest(FundOrder fundOrder) {
    this.fundOrder = fundOrder;

  }

  public UpdateFundOrderRequest(FundOrder fundOrder, List<FundOrderAttachment> attachments) {
    this.fundOrder = fundOrder;
    this.attachments = attachments;
  }

  public UpdateFundOrderRequest(FundOrder fundOrder, Long customerId,
      List<FundOrderAttachment> attachments) {
    this.fundOrder = fundOrder;
    this.attachments = attachments;
    this.customerId = customerId;
  }
}
