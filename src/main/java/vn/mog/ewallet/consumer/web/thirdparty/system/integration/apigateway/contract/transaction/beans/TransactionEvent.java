package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans;

import java.io.Serializable;
import java.util.Date;

public class TransactionEvent implements Serializable {

  private static final long serialVersionUID = 1L;
  private Long id;
  private Long txnId;
  private String extTxnRefId;
  private String actorId;
  private String actorType;
  private String eventType;
  private String eventData;
  private String errorCode;
  private String errorMessage;
  private Long refEventId;
  private Date startTime;
  private Date endTime;
  private Date creationDate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getTxnId() {
    return txnId;
  }

  public void setTxnId(Long txnId) {
    this.txnId = txnId;
  }

  public String getExtTxnRefId() {
    return extTxnRefId;
  }

  public void setExtTxnRefId(String extTxnRefId) {
    this.extTxnRefId = extTxnRefId;
  }

  public String getActorId() {
    return actorId;
  }

  public void setActorId(String actorId) {
    this.actorId = actorId;
  }

  public String getActorType() {
    return actorType;
  }

  public void setActorType(String actorType) {
    this.actorType = actorType;
  }

  public String getEventType() {
    return eventType;
  }

  public void setEventType(String eventType) {
    this.eventType = eventType;
  }

  public String getEventData() {
    return eventData;
  }

  public void setEventData(String eventData) {
    this.eventData = eventData;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public Long getRefEventId() {
    return refEventId;
  }

  public void setRefEventId(Long refEventId) {
    this.refEventId = refEventId;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  // public SubTransaction() {
  // super();
  // }
  //
  // public SubTransaction(Long id, Date creationDate, String actorId, String actorType,
  // String errorCode, String errorMessage, String eventData, String eventType, Long refEventId,
  // String txnId, String txnRef, Date startTime, Date endTime) {
  // super();
  // this.id = id;
  // this.creationDate = creationDate;
  // this.actorId = actorId;
  // this.actorType = actorType;
  // this.errorCode = errorCode;
  // this.errorMessage = errorMessage;
  // this.eventData = eventData;
  // this.eventType = eventType;
  // this.refEventId = refEventId;
  // this.txnId = txnId;
  // this.txnRef = txnRef;
  // this.startTime = startTime;
  // this.endTime = endTime;
  // }

}
