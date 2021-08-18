package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet;

import java.io.Serializable;

public class CreateWalletTransferOrderRequest extends CreateWalletTransferOrderRequestType
    implements Serializable {

  private static final long serialVersionUID = 1L;

  private String phone_number_payer;
  private String phone_number_payee;
  private Long amount;
  private String remark;
  private String channel;

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public String getPhone_number_payer() {
    return phone_number_payer;
  }

  public void setPhone_number_payer(String phone_number_payer) {
    this.phone_number_payer = phone_number_payer;
  }

  public String getPhone_number_payee() {
    return phone_number_payee;
  }

  public void setPhone_number_payee(String phone_number_payee) {
    this.phone_number_payee = phone_number_payee;
  }

  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getChannel() {
    return channel;
  }

  public void setChannel(String channel) {
    this.channel = channel;
  }
}
