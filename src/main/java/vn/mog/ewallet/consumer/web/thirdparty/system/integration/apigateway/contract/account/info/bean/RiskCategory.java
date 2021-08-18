package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info.bean;

import java.util.Date;

public class RiskCategory {
  private Integer id;
  private String name;
  private String role;
  private Long limitSetId;

  private Date created;

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getRole() {
    return role;
  }

  public Long getLimitSetId() {
    return limitSetId;
  }

  public Date getCreated() {
    return created;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public void setLimitSetId(Long limitSetId) {
    this.limitSetId = limitSetId;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

}
