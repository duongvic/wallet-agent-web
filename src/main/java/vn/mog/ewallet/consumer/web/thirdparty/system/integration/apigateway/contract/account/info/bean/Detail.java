package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info.bean;

public class Detail {

  private Boolean idValid;
  private Boolean fullnameValid;
  private Boolean nationValid;
  private Boolean photoValid;
  private Boolean birthdayValid;

  public Boolean getIdValid() {
    return idValid;
  }

  public void setIdValid(Boolean idValid) {
    this.idValid = idValid;
  }

  public Boolean getFullnameValid() {
    return fullnameValid;
  }

  public void setFullnameValid(Boolean fullnameValid) {
    this.fullnameValid = fullnameValid;
  }

  public Boolean getNationValid() {
    return nationValid;
  }

  public void setNationValid(Boolean nationValid) {
    this.nationValid = nationValid;
  }

  public Boolean getPhotoValid() {
    return photoValid;
  }

  public void setPhotoValid(Boolean photoValid) {
    this.photoValid = photoValid;
  }

  public Boolean getBirthdayValid() {
    return birthdayValid;
  }

  public void setBirthdayValid(Boolean birthdayValid) {
    this.birthdayValid = birthdayValid;
  }

  public String message() {
    boolean first = true;
    StringBuilder sb = new StringBuilder();
    if (!idValid) {
      sb.append((first ? "" : ", ") + "Id is invalid <br/>");
      first = false;
    }

    if (!fullnameValid) {
      sb.append((first ? "" : ", ") + "fullname is invalid <br/>");
      first = false;
    }

    if (!nationValid) {
      sb.append((first ? "" : ", ") + "nation is invalid <br/>");
      first = false;
    }

    if (!photoValid) {
      sb.append((first ? "" : ", ") + "photo is invalid <br/>");
      first = false;
    }
    if (!birthdayValid) {
      sb.append((first ? "" : ", ") + "birthday is invalid <br/>");
      first = false;
    }
    return sb.toString();
  }
}
