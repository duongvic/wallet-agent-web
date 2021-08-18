package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.bean;


public class CustomerProfileStatus {

  private Integer email;
  private Integer account;
  private Integer linkBank;
  private Integer securePayment;
  
  public CustomerProfileStatus() {
  }
  
  public CustomerProfileStatus(Integer email, Integer account, Integer linkBank,
      Integer securePayment) {
    this.email = email;
    this.account = account;
    this.linkBank = linkBank;
    this.securePayment = securePayment;
  }


  public Integer getEmail() {
    return email;
  }
  public Integer getAccount() {
    return account;
  }
  public Integer getLinkBank() {
    return linkBank;
  }
  public Integer getSecurePayment() {
    return securePayment;
  }
  public void setEmail(Integer email) {
    this.email = email;
  }
  public void setAccount(Integer account) {
    this.account = account;
  }
  public void setLinkBank(Integer linkBank) {
    this.linkBank = linkBank;
  }
  public void setSecurePayment(Integer securePayment) {
    this.securePayment = securePayment;
  }
  
  
}
