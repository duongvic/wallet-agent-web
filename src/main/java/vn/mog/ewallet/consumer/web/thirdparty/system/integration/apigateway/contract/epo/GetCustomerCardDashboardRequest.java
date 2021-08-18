package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo;

import java.util.Date;
import java.util.List;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class GetCustomerCardDashboardRequest extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  private List<String> providers;
  private List<String> cardTypes;
  private List<Integer> faceValues;
  private List<String> status;
  private List<String> stages;

  private Date fromDate;
  private Date endDate;

  public List<String> getProviders() {
    return providers;
  }

  public void setProviders(List<String> providers) {
    this.providers = providers;
  }

  public List<String> getCardTypes() {
    return cardTypes;
  }

  public void setCardTypes(List<String> cardTypes) {
    this.cardTypes = cardTypes;
  }

  public List<Integer> getFaceValues() {
    return faceValues;
  }

  public void setFaceValues(List<Integer> faceValues) {
    this.faceValues = faceValues;
  }

  public List<String> getStatus() {
    return status;
  }

  public void setStatus(List<String> status) {
    this.status = status;
  }

  public List<String> getStages() {
    return stages;
  }

  public void setStages(List<String> stages) {
    this.stages = stages;
  }

  public Date getFromDate() {
    return fromDate;
  }

  public void setFromDate(Date fromDate) {
    this.fromDate = fromDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }
}
