package vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans;

import java.io.Serializable;
import org.springframework.security.core.GrantedAuthority;

public class JwtPermission implements GrantedAuthority, Serializable {

  private static final long serialVersionUID = 1L;
  private String name;
  private String description;
  private String type;

  private Boolean active;

  public JwtPermission() {
  }

  public JwtPermission(Long id, String name, String description, String type, Boolean active) {
    super();
    this.name = name;
    this.description = description;
    this.type = type;
    this.active = active;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  @Override
  public String getAuthority() {
    return name;
  }

}
