package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.provider;

import java.io.Serializable;


/**
 * Created by binhminh on 14/02/2017.
 */
public class UpdateProviderRequest extends UpdateProviderRequestType implements Serializable {

  private static final long serialVersionUID = 1L;

  public UpdateProviderRequest(Long providerId, String name, Double score) {
    this.providerId = providerId;
    this.name = name;
    this.score = score;
  }

  public UpdateProviderRequest() {

  }
}
