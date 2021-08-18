package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.recentness.bean;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Recentness implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;
  @JsonProperty("recentness_key")
  private String recentnessKey;
  private String value;
  
  public Recentness() {
    super();
  }
  
  public Recentness(Long id, String recentnessKey, String value) {
    super();
    this.id = id;
    this.recentnessKey = recentnessKey;
    this.value = value;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRecentnessKey() {
    return recentnessKey;
  }

  public void setRecentnessKey(String recentnessKey) {
    this.recentnessKey = recentnessKey;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
