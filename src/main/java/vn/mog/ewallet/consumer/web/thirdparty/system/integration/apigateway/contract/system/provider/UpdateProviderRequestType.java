package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.provider;

import java.io.Serializable;
import vn.mog.framework.contract.base.MobiliserRequestType;

/**
 * Created by binhminh on 14/02/2017.
 */
public class UpdateProviderRequestType extends MobiliserRequestType implements Serializable {

  private static final long serialVersionUID = 1L;
  protected Long providerId;
  protected String name;
  protected Double score;


  public Long getProviderId() {
    return providerId;
  }

  public void setProviderId(Long providerId) {
    this.providerId = providerId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getScore() {
    return score;
  }

  public void setScore(Double score) {
    this.score = score;
  }
}
