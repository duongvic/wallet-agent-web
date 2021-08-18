package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by binhminh on 06/09/2017.
 */
public class ServiceAttributeType {

  public final static transient String TELCO_TYPE = "TELCO_TYPE";
  public final static transient String TELCO_SERVICE_TYPE = "TELCO_SERVICE_TYPE";
  public final static transient String ORDER_CHANNEL = "ORDER_CHANNEL";
  public final static transient String BANK_CODE = "BANK_CODE";

  public static final Map<String, String> SERVICE_ATTRIBUTE_TYPES = new LinkedHashMap<>();

  static {
    SERVICE_ATTRIBUTE_TYPES.put(TELCO_TYPE, "Telco Type");
    SERVICE_ATTRIBUTE_TYPES.put(TELCO_SERVICE_TYPE, "Telco Service Type");
    SERVICE_ATTRIBUTE_TYPES.put(ORDER_CHANNEL, "Order Channel");
    SERVICE_ATTRIBUTE_TYPES.put(BANK_CODE, "Bank Code");

  }
}
