package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info.bean;

public class CustomerAttributeType {
	private static final long serialVersionUID = 1L;

	public static final String tableName = "MOB_CUSTOMER_ATTRIBUTE_TYPES";

	public final static transient Integer CREATE_SERVICE_ACCOUNT_STATUS = 1;
	public final static transient Integer CREATE_PAYMENT_ACCOUNT_STATUS = 2;
	public final static transient Integer UPDATE_SERVICE_ACCOUNT_STATUS = 3;
	public final static transient Integer UPDATE_PAYMENT_ACCOUNT_STATUS = 4;
	
	public final static transient Integer MSISDN_OTP = 300;
	public final static transient Integer MSISDN_PASSWORD = 301;
	public final static transient Integer ALLOWED_IPS = 302;
	
	public final static transient Integer GA_SECRET = 350;
	public final static transient Integer GA_URL = 351;
	
	public final static transient Integer TWO_FACTOR_SECURITY_SMS_ENABLE = 401;
	public final static transient Integer TWO_FACTOR_SECURITY_GOOGLE_AUTHENTICATOR_ENABLE = 402;
	

	private String description;

	private Character dbInternal = Character.valueOf('N');

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getInternal() {
		return this.dbInternal.equals(Character.valueOf('Y'));
	}

	public boolean isSetInternal() {
		return (this.dbInternal != null);
	}

	public void setInternal(boolean internal) {
		this.dbInternal = Character.valueOf((internal) ? 'Y' : 'N');
	}
}
