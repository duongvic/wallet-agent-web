package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.recentness;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class RecentnessGetRequest extends MobiliserRequestType {
  private static final long serialVersionUID = 1L;

  private String recentnessKey;
  private Integer offset;
  private Integer limit;

  public String getRecentnessKey() {
    return recentnessKey;
  }

  public void setRecentnessKey(String recentnessKey) {
    this.recentnessKey = recentnessKey;
  }

  public Integer getOffset() {
    return offset;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }
}
