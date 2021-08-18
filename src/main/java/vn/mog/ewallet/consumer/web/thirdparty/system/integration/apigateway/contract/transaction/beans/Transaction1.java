package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.FundOrderChannelType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.ServiceType;

public class Transaction1 implements Serializable {

  private static final long serialVersionUID = 1L;
  private Long id;
  private String traceNo;
  private Long refTxnId;

  @JsonProperty("service_type")
  private String serviceType;//EPIN"", // Loại dịch vụ
  // Service
  private Long serviceId;

  @JsonProperty("service_code")
  private String serviceCode;

  @JsonProperty("service_name")
  private String serviceName;

  @JsonProperty("terminal_id")
  private String terminalId;

  @JsonProperty("order_channel")
  private String orderChannel;

  @JsonProperty("order_id")
  private String orderId;

  @JsonProperty("order_info")
  private String orderInfo;

  private String cif;
  private String username;

  private Long payerId;

  @JsonProperty("payer_username")
  private String payerUsername;

  private Long payeeId;

  @JsonProperty("payee_username")
  private String payeeUsername;
  private String creatorUsername;
  private String approverUsername;

  private Long amount;
  private String currency;
  private Long fee;
  private Long commision;
  private Long capitalValue;

  @JsonProperty("real_amount")
  private Long realAmount;

  @JsonProperty("pre_balance")
  private Long preBalance;

  @JsonProperty("post_balance")
  private Long postBalance;

  private Boolean test;
  private Boolean locked;
  private Boolean autoCapture;
  private Date expiration;

  private String text;
  private String providerCode;

  private TransactionEvent1 lastEvent;
  private Date lastEventTime;

  @JsonProperty("error_code")
  private Integer errorCode;

  @JsonProperty("error_message")
  private String errorMessage;

  @JsonProperty("transaction_status")
  private Integer transactionStatus;

  @JsonProperty("creation_date")
  private Date creationDate;

  // TransResult (bank)
  private List<Transaction1.Card> serials;

  // Transaction Attribute (we will order this)
  private List<TransactionAttribute1> attributes;

  public Transaction1() {
    super();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public Long getPayeeId() {
    return payeeId;
  }

  public void setPayeeId(Long payeeId) {
    this.payeeId = payeeId;
  }

  public String getPayerUsername() {
    return payerUsername;
  }

  public void setPayerUsername(String payerUsername) {
    this.payerUsername = payerUsername;
  }

  public String getPayeeUsername() {
    return payeeUsername;
  }

  public void setPayeeUsername(String payeeUsername) {
    this.payeeUsername = payeeUsername;
  }

  public String getCreatorUsername() {
    return creatorUsername;
  }

  public void setCreatorUsername(String creatorUsername) {
    this.creatorUsername = creatorUsername;
  }

  public String getApproverUsername() {
    return approverUsername;
  }

  public void setApproverUsername(String approverUsername) {
    this.approverUsername = approverUsername;
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

  public TransactionEvent1 getLastEvent() {
    return lastEvent;
  }

  public void setLastEvent(TransactionEvent1 lastEvent) {
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

  public Integer getTransactionStatus() {
    return transactionStatus;
  }

  public void setTransactionStatus(Integer transactionStatus) {
    this.transactionStatus = transactionStatus;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public List<Transaction1.Card> getSerials() {
    return serials;
  }

  public void setSerials(List<Transaction1.Card> serials) {
    this.serials = serials;
  }

  public String getTransactionType() {
    if (ServiceType.FUND_IN.name().equals(serviceType)) {
      return "+";
    } else {
      return "-";
    }
  }

  public String getService() {
    return ServiceType.SERVICE_TYPES.get(serviceType);
  }

  public String getStatus() {
    return TxnStatus.TRANSACTION_STATUSES.get(transactionStatus);
  }

  public String getStringStatus() {
    switch (transactionStatus) {
      case TxnStatus.CANCELLED:
        return "CANCELLED";
      case TxnStatus.CLOSED:
        return "CLOSED";
      case TxnStatus.FAILED:
        return "FAILED";
      case TxnStatus.INITIAL:
        return "INITIAL";
      case TxnStatus.OPENED:
        return "OPENED";
      case TxnStatus.REVERSED:
        return "REVERSED";
      default:
        return StringUtils.EMPTY;
    }
  }

  public String textOrderChannel(String orderChannel) {

    if (FundOrderChannelType.BANK_TRANSFER.code.equals(orderChannel)) {
      return FundOrderChannelType.BANK_TRANSFER.displayText;

    } else if (FundOrderChannelType.LINK_BANK.code.equals(orderChannel)) {
      return FundOrderChannelType.LINK_BANK.displayText;

    } else if (FundOrderChannelType.CASH_ON_HAND.code.equals(orderChannel)) {
      return FundOrderChannelType.CASH_ON_HAND.displayText;
    }
    return orderChannel;
  }

  public boolean isBuyCard() {
    return ServiceType.EPIN.name().equals(serviceType);
  }

  public List<TransactionAttribute1> getAttributes() {
    if (attributes == null) {
      attributes = Collections.emptyList();
    }
    return attributes;
  }

  public void setAttributes(List<TransactionAttribute1> attributes) {
    this.attributes = attributes;
  }


  public static class Card implements Serializable {

    private String serial;
    private String pin;
    private Integer price;
    private String cardType;
    private Date expiredDate;

    public Card() {
    }

    public Card(String serial, String pin, Integer price, String cardType, Date expiredDate) {
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

    public Integer getPrice() {
      return price;
    }

    public void setPrice(Integer price) {
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
