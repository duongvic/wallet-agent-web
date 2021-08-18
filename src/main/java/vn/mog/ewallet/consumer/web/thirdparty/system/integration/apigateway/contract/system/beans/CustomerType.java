package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class CustomerType implements Serializable {

  public final static transient Integer ID_CUSTOMER = 1;
  public final static transient Integer ID_AGENT = 2;
  public final static transient Integer ID_MERCHANT = 3;
  public final static transient Integer ID_SALE = 4;
  public final static transient Integer ID_ZOTA = 5;
  public final static transient Integer ID_POOL = 6;
  public final static transient Integer ID_SOF = 7;
  public final static transient Integer ID_STAFF = 8;
  public final static transient Integer ID_ADMIN = 9;
  public final static transient Integer ID_SYSTEM = 10;
  public final static transient Integer ID_PROVIDER = 11;

  public final static transient Integer CUST_ACTIVE = 1;
  public final static transient Integer CUST_IN_ACTIVE = 0;

  public final static transient Integer USER_TYPE_SYS = 1;
  public final static transient Integer USER_TYPE_USER = 2;

  public final static transient Integer ACCOUNT = 1;
  public final static transient Integer WALLET = 2;

  public static final Map<Integer, String> CUSTOMER_TYPE_IDS = new LinkedHashMap<>();
  public static final Map<Integer, String> CUSTOMER_STATUS_IDS = new LinkedHashMap<>();
  public static final Map<Integer, String> SERVICE_TYPE_IDS = new LinkedHashMap<>();
  public static final Map<Integer, String> WALLET_TYPE_IDS = new LinkedHashMap<>();

  private static final long serialVersionUID = 1L;

  static {
    CUSTOMER_TYPE_IDS.put(ID_CUSTOMER, "Customer");
    CUSTOMER_TYPE_IDS.put(ID_AGENT, "Agent");
    CUSTOMER_TYPE_IDS.put(ID_MERCHANT, "Merchant");
    CUSTOMER_TYPE_IDS.put(ID_SALE, "Sale");
    CUSTOMER_TYPE_IDS.put(ID_ZOTA, "ZO-TA");
    CUSTOMER_TYPE_IDS.put(ID_POOL, "Pool");
    CUSTOMER_TYPE_IDS.put(ID_STAFF, "Staff");
    CUSTOMER_TYPE_IDS.put(ID_SOF, "SOF");
    CUSTOMER_TYPE_IDS.put(ID_ADMIN, "Admin");
    CUSTOMER_TYPE_IDS.put(ID_SYSTEM, "System");
    CUSTOMER_TYPE_IDS.put(ID_PROVIDER, "Provider");

    CUSTOMER_STATUS_IDS.put(CUST_IN_ACTIVE, "user.status.inactive");
    CUSTOMER_STATUS_IDS.put(CUST_ACTIVE, "user.status.active");

    SERVICE_TYPE_IDS.put(USER_TYPE_SYS, "System");
    SERVICE_TYPE_IDS.put(USER_TYPE_USER, "User");

    WALLET_TYPE_IDS.put(ACCOUNT, "Account");
    WALLET_TYPE_IDS.put(WALLET, "Wallet");
  }

  protected Integer id;
  protected String name;
  protected String role;
  protected boolean internal;

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer value) {
    this.id = value;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String value) {
    this.name = value;
  }

  public String getRole() {
    return this.role;
  }

  public void setRole(String value) {
    this.role = value;
  }

  public boolean isInternal() {
    return this.internal;
  }

  public void setInternal(boolean value) {
    this.internal = value;
  }
}
