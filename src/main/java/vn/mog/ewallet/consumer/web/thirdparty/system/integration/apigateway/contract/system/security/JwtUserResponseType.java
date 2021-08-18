package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security;

import java.io.Serializable;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans.JwtUser;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class JwtUserResponseType extends MobiliserResponseType implements Serializable {

  private static final long serialVersionUID = 1L;
  protected JwtUser jwtUser;

  public JwtUser getJwtUser() {
    return jwtUser;
  }

  public void setJwtUser(JwtUser jwtUser) {
    this.jwtUser = jwtUser;
  }
}
