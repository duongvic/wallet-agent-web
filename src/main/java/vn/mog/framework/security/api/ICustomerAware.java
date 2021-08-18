package vn.mog.framework.security.api;

public interface ICustomerAware {

  public long getCustomerId();

  public String getCustomerCif();

  public String getUsername();

  public int getCustomerTypeId();

  public String getPhoneNumber();

}
