package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet;

import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.bean.WalletTransferOrder;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class MoneyTransferRequest extends MobiliserRequestType{

  private static final long serialVersionUID = 1L;

  private WalletTransferOrder order;

  private String actionType; // (M) // VERIFY or CONFIRM

  private Long orderId;

  private String otp;

  public WalletTransferOrder getOrder() {
    return order;
  }

  public void setOrder(WalletTransferOrder order) {
    this.order = order;
  }

  public String getActionType() {
    return actionType;
  }

  public void setActionType(String actionType) {
    this.actionType = actionType;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public String getOtp() {
    return otp;
  }

  public void setOtp(String otp) {
    this.otp = otp;
  }

}
