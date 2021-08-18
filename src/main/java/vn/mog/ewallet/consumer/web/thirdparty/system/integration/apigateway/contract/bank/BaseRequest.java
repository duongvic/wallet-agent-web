package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank;


import vn.mog.ewallet.consumer.web.common.util.Utils;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class BaseRequest extends MobiliserRequestType {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Override
  public String toString() {
    return Utils.convertToString(this);
  }
}
