package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.TelcoType;

public class Transaction implements Serializable {

  private static final long serialVersionUID = 1L;
  private Long id;

  private Long idOwner;

  private Integer idOwnerCustomerType;

  private String traceNo;

  private Long refTxnId;

  private String serviceType;

  // Service
  private Long serviceId;

  private String serviceCode;

  private String serviceName;

  private String terminalId;

  private String orderChannel;

  private String orderId;

  private String orderInfo;

  private String cif;

  private String username;

  private Long payerId;

  private String payerUsername;

  private String payerFullname;

  private String payerMsisdn;

  private Long payeeId;

  private String payeeUsername;

  private String payeeFullname;

  private String payeeMsisdn;

  private Long amount;

  private String currency;

  private Long fee;

  private Long commision;

  private Long capitalValue;

  private Long realAmount;

  private Long preBalance;

  private Long postBalance;

  private Boolean test;

  private Boolean locked;

  private Boolean autoCapture;

  private Date expiration;

  private String text;

  private String providerCode;

  private TransactionEvent lastEvent;

  private Date lastEventTime;

  private Integer errorCode;

  private String errorMessage;

  private int transactionStatus;

  private Date creationDate;

  private List<Card> serials;

  // Transaction Attribute (we will order this)
  private List<TransactionAttribute> attributes;

  private String bankCode;

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  @JsonIgnore
  private String bankName;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getIdOwner() {
    return idOwner;
  }

  public void setIdOwner(Long idOwner) {
    this.idOwner = idOwner;
  }

  public Integer getIdOwnerCustomerType() {
    return idOwnerCustomerType;
  }

  public void setIdOwnerCustomerType(Integer idOwnerCustomerType) {
    this.idOwnerCustomerType = idOwnerCustomerType;
  }

  public String getTraceNo() {
    return traceNo;
  }

  public void setTraceNo(String traceNo) {
    this.traceNo = traceNo;
  }

  public Long getRefTxnId() {
    return refTxnId;
  }

  public void setRefTxnId(Long refTxnId) {
    this.refTxnId = refTxnId;
  }

  public String getServiceType() {
    return serviceType;
  }

  public void setServiceType(String serviceType) {
    this.serviceType = serviceType;
  }

  public Long getServiceId() {
    return serviceId;
  }

  public void setServiceId(Long serviceId) {
    this.serviceId = serviceId;
  }

  public String getServiceCode() {
    return serviceCode;
  }

  public void setServiceCode(String serviceCode) {
    this.serviceCode = serviceCode;
  }

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public String getTerminalId() {
    return terminalId;
  }

  public void setTerminalId(String terminalId) {
    this.terminalId = terminalId;
  }

  public String getOrderChannel() {
    return orderChannel;
  }

  public void setOrderChannel(String orderChannel) {
    this.orderChannel = orderChannel;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getOrderInfo() {
    return orderInfo;
  }

  public void setOrderInfo(String orderInfo) {
    this.orderInfo = orderInfo;
  }

  public String getCif() {
    return cif;
  }

  public void setCif(String cif) {
    this.cif = cif;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Long getPayerId() {
    return payerId;
  }

  public void setPayerId(Long payerId) {
    this.payerId = payerId;
  }

  public String getPayerUsername() {
    return payerUsername;
  }

  public void setPayerUsername(String payerUsername) {
    this.payerUsername = payerUsername;
  }

  public String getPayerFullname() {
    return payerFullname;
  }

  public void setPayerFullname(String payerFullname) {
    this.payerFullname = payerFullname;
  }

  public Long getPayeeId() {
    return payeeId;
  }

  public void setPayeeId(Long payeeId) {
    this.payeeId = payeeId;
  }

  public String getPayeeUsername() {
    return payeeUsername;
  }

  public void setPayeeUsername(String payeeUsername) {
    this.payeeUsername = payeeUsername;
  }

  public String getPayeeFullname() {
    return payeeFullname;
  }

  public void setPayeeFullname(String payeeFullname) {
    this.payeeFullname = payeeFullname;
  }

  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public Long getFee() {
    return fee;
  }

  public void setFee(Long fee) {
    this.fee = fee;
  }

  public Long getCommision() {
    return commision;
  }

  public void setCommision(Long commision) {
    this.commision = commision;
  }

  public Long getCapitalValue() {
    return capitalValue;
  }

  public void setCapitalValue(Long capitalValue) {
    this.capitalValue = capitalValue;
  }

  public Long getRealAmount() {
    return realAmount;
  }

  public void setRealAmount(Long realAmount) {
    this.realAmount = realAmount;
  }

  public Date getExpiration() {
    return expiration;
  }

  public void setExpiration(Date expiration) {
    this.expiration = expiration;
  }

  public Boolean getTest() {
    return test;
  }

  public void setTest(Boolean test) {
    this.test = test;
  }

  public Boolean getLocked() {
    return locked;
  }

  public void setLocked(Boolean locked) {
    this.locked = locked;
  }

  public Boolean getAutoCapture() {
    return autoCapture;
  }

  public void setAutoCapture(Boolean autoCapture) {
    this.autoCapture = autoCapture;
  }

  public Long getPreBalance() {
    return preBalance;
  }

  public void setPreBalance(Long preBalance) {
    this.preBalance = preBalance;
  }

  public Long getPostBalance() {
    return postBalance;
  }

  public void setPostBalance(Long postBalance) {
    this.postBalance = postBalance;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getProviderCode() {
    return providerCode;
  }

  public void setProviderCode(String providerCode) {
    this.providerCode = providerCode;
  }

  public TransactionEvent getLastEvent() {
    return lastEvent;
  }

  public void setLastEvent(TransactionEvent lastEvent) {
    this.lastEvent = lastEvent;
  }

  public Date getLastEventTime() {
    return lastEventTime;
  }

  public void setLastEventTime(Date lastEventTime) {
    this.lastEventTime = lastEventTime;
  }

  public Integer getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(Integer errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public int getTransactionStatus() {
    return transactionStatus;
  }

  public void setTransactionStatus(int transactionStatus) {
    this.transactionStatus = transactionStatus;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public List<Card> getSerials() {
    return serials;
  }

  public void setSerials(List<Card> serials) {
    this.serials = serials;
  }

  public List<TransactionAttribute> getAttributes() {
    return attributes;
  }

  public String getAttributeValueByAttributeType(String attributeType) {
    for (TransactionAttribute item : attributes) {
      if (attributeType.equals(item.getTransactionAttributeType())) {
        return item.getTransactionAttributeValue();
      }
    }
    return null;
  }

  public void setAttributes(List<TransactionAttribute> attributes) {
    this.attributes = attributes;
  }

  public Map<String, String> getAttributeMap() {
    Map<String, String> attributeMap = new HashMap<>();
    for (TransactionAttribute item : attributes) {
      if ("PTU_CARD_TYPE".equals(item.getTransactionAttributeType()) || "PTU_TELCO".equals(item.getTransactionAttributeType())) {
        TelcoType telcoType = TelcoType.getTelcoType(item.getTransactionAttributeValue());
        attributeMap.put(item.getTransactionAttributeType(), telcoType != null ? telcoType.getName().toLowerCase() : StringUtils.EMPTY);
      } else {
        attributeMap.put(item.getTransactionAttributeType(), item.getTransactionAttributeValue());
      }
    }

    return attributeMap;
  }

  public String getBankCode() {
    return bankCode;
  }

  public void setBankCode(String bankCode) {
    this.bankCode = bankCode;
  }

  public String getPayerMsisdn() {
    return payerMsisdn;
  }

  public void setPayerMsisdn(String payerMsisdn) {
    this.payerMsisdn = payerMsisdn;
  }

  public String getPayeeMsisdn() {
    return payeeMsisdn;
  }

  public void setPayeeMsisdn(String payeeMsisdn) {
    this.payeeMsisdn = payeeMsisdn;
  }

  public static class Card {

    private String serial;
    private String pin;
    private Long price;
    private String cardType;
    private Date expiredDate;

    public Card() {
    }

    public Card(String serial, String pin, Long price, String cardType, Date expiredDate) {
      super();
      this.serial = serial;
      this.pin = pin;
      this.price = price;
      this.cardType = cardType;
      this.expiredDate = expiredDate;
    }

    public String getSerial() {
      return serial;
    }

    public void setSerial(String serial) {
      this.serial = serial;
    }

    public String getPin() {
      return pin;
    }

    public void setPin(String pin) {
      this.pin = pin;
    }

    public Long getPrice() {
      return price;
    }

    public void setPrice(Long price) {
      this.price = price;
    }

    public String getCardType() {
      return cardType;
    }

    public void setCardType(String cardType) {
      this.cardType = cardType;
    }

    public Date getExpiredDate() {
      return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
      this.expiredDate = expiredDate;
    }


  }
}
