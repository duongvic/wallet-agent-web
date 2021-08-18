package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class PurchaseOrderItem implements Serializable {

  private Long id;
  private String poCode;
  private String provider;
  private Long totalQuantity;
  private Long totalValue;
  private Long capitalValue;
  private String workflowState;
  private String status;
  private String note;
  private String filePath;
  private List<PurchaseOrderDetailItem> purchaseOrderDetails;

  private Date lastUpdate;
  private Long lastUpdater;
  private Date creationDate;
  private Long creator;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPoCode() {
    return poCode;
  }

  public void setPoCode(String poCode) {
    this.poCode = poCode;
  }

  public String getProvider() {
    return provider;
  }

  public void setProvider(String provider) {
    this.provider = provider;
  }

  public Long getTotalQuantity() {
    return totalQuantity;
  }

  public void setTotalQuantity(Long totalQuantity) {
    this.totalQuantity = totalQuantity;
  }

  public Long getTotalValue() {
    return totalValue;
  }

  public void setTotalValue(Long totalValue) {
    this.totalValue = totalValue;
  }

  public Long getCapitalValue() {
    return capitalValue;
  }

  public void setCapitalValue(Long capitalValue) {
    this.capitalValue = capitalValue;
  }

  public String getWorkflowState() {
    return workflowState;
  }

  public void setWorkflowState(String workflowState) {
    this.workflowState = workflowState;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public List<PurchaseOrderDetailItem> getPurchaseOrderDetails() {
    return purchaseOrderDetails;
  }

  public void setPurchaseOrderDetails(List<PurchaseOrderDetailItem> purchaseOrderDetails) {
    this.purchaseOrderDetails = purchaseOrderDetails;
  }

  public Date getLastUpdate() {
    return lastUpdate;
  }

  public void setLastUpdate(Date lastUpdate) {
    this.lastUpdate = lastUpdate;
  }

  public Long getLastUpdater() {
    return lastUpdater;
  }

  public void setLastUpdater(Long lastUpdater) {
    this.lastUpdater = lastUpdater;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public Long getCreator() {
    return creator;
  }

  public void setCreator(Long creator) {
    this.creator = creator;
  }

}
