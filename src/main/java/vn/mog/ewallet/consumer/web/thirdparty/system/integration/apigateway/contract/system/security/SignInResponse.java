package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class SignInResponse extends MobiliserResponseType {

 private String scope;
 private Long id;
 private String cif;
 private String fullname;
 private String email;
 private String accessToken;
 private long expiresIn;
 private String refreshToken;
 private Integer type;

 @JsonIgnore
 private String correlationId;
 @JsonIgnore
 private String tokenType;
 @JsonIgnore
 private String error;
 @JsonIgnore
 private String errorDescription;
 @JsonIgnore
 private String result;
 @JsonIgnore
 private String jti;

 public String getAccessToken() {
  return accessToken;
 }

 public long getExpiresIn() {
  return expiresIn;
 }

 public String getRefreshToken() {
  return refreshToken;
 }

 public String getCorrelationId() {
  return correlationId;
 }

 public void setAccessToken(String accessToken) {
  this.accessToken = accessToken;
 }

 public void setExpiresIn(long expiresIn) {
  this.expiresIn = expiresIn;
 }

 public void setRefreshToken(String refreshToken) {
  this.refreshToken = refreshToken;
 }

 public void setCorrelationId(String correlationId) {
  this.correlationId = correlationId;
 }

 public String getTokenType() {
  return tokenType;
 }

 public void setTokenType(String tokenType) {
  this.tokenType = tokenType;
 }

 public String getScope() {
  return scope;
 }

 public void setScope(String scope) {
  this.scope = scope;
 }

 public String getError() {
  return error;
 }

 public void setError(String error) {
  this.error = error;
 }

 public String getErrorDescription() {
  return errorDescription;
 }

 public void setErrorDescription(String errorDescription) {
  this.errorDescription = errorDescription;
 }

 public String getResult() {
  return result;
 }

 public void setResult(String result) {
  this.result = result;
 }

 public Long getId() {
  return id;
 }

 public void setId(Long id) {
  this.id = id;
 }

 public String getCif() {
  return cif;
 }

 public void setCif(String cif) {
  this.cif = cif;
 }

 public String getFullname() {
  return fullname;
 }

 public void setFullname(String fullname) {
  this.fullname = fullname;
 }

 public Integer getType() {
  return type;
 }

 public void setType(Integer type) {
  this.type = type;
 }

 public String getJti() {
  return jti;
 }

 public void setJti(String jti) {
  this.jti = jti;
 }

 public String getEmail() {
  return email;
 }

 public void setEmail(String email) {
  this.email = email;
 }
}
