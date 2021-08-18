package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info.bean;

import java.math.BigDecimal;

public class OrgUnit {

  private String id;

  private String name;

  private String currency;

  private BigDecimal vat;

  private String timeZone;

  private String language;

  private String country;

  private Long legalAddressId;

  private Long limitSetId;

  private Integer autoCancelAfterMinutes;

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getCurrency() {
    return currency;
  }

  public BigDecimal getVat() {
    return vat;
  }

  public String getTimeZone() {
    return timeZone;
  }

  public String getLanguage() {
    return language;
  }

  public String getCountry() {
    return country;
  }

  public Long getLegalAddressId() {
    return legalAddressId;
  }

  public Long getLimitSetId() {
    return limitSetId;
  }

  public Integer getAutoCancelAfterMinutes() {
    return autoCancelAfterMinutes;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public void setVat(BigDecimal vat) {
    this.vat = vat;
  }

  public void setTimeZone(String timeZone) {
    this.timeZone = timeZone;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public void setLegalAddressId(Long legalAddressId) {
    this.legalAddressId = legalAddressId;
  }

  public void setLimitSetId(Long limitSetId) {
    this.limitSetId = limitSetId;
  }

  public void setAutoCancelAfterMinutes(Integer autoCancelAfterMinutes) {
    this.autoCancelAfterMinutes = autoCancelAfterMinutes;
  }

}
