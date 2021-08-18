package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer;

public class SendUserAccountInforNotificationRequest extends
    SendUserAccountInforNotificationRequestType {

  private static final long serialVersionUID = 1L;

  private String cif;

  public SendUserAccountInforNotificationRequest() {
  }

  public SendUserAccountInforNotificationRequest(String cif) {
    this.cif = cif;
  }

  public String getCif() {
    return cif;
  }

  public void setCif(String cif) {
    this.cif = cif;
  }
}
