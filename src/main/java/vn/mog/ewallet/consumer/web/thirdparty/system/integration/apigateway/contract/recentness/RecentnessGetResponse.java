package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.recentness;

import java.io.Serializable;
import java.util.List;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.recentness.bean.Recentness;
import vn.mog.framework.contract.base.MobiliserResponseType;


public class RecentnessGetResponse extends MobiliserResponseType {
  private static final long serialVersionUID = 1L;

  private List<Recentness> recentnesses;

  public List<Recentness> getRecentnesses() {
    return recentnesses;
  }

  public void setRecentnesses(List<Recentness> recentnesses) {
    this.recentnesses = recentnesses;
  }
}
