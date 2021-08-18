package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank;


import vn.mog.ewallet.consumer.web.common.util.Utils;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class BaseResponse extends MobiliserResponseType {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Override
  public String toString() {
    return Utils.convertToString(this);
  }
}
