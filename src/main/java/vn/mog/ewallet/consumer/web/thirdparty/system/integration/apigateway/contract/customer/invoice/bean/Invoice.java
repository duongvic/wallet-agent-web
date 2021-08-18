package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice.bean;

import java.io.Serializable;
import java.util.Currency;
import java.util.Date;
import java.util.Set;

public class Invoice implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long invoiceId;
  private Long serviceId;
  private String invoiceReference;
  private String customerReference;
  private Long amount;
  private Long cashBackAmount;
  private String currency;
  private String info;
  private Date creationDate;
  private Date dueDate;
  private Date payDate;
  private Date expirationDate;
  private Integer status = Integer.valueOf(0);
  private Boolean isPartialPaymentAllowed;
  private Set<InvoiceAttribute> invoiceAttributes;

  public Long getInvoiceId() {
    return invoiceId;
  }

  public void setInvoiceId(Long invoiceId) {
    this.invoiceId = invoiceId;
  }

  public Long getServiceId() {
    return serviceId;
  }

  public void setServiceId(Long serviceId) {
    this.serviceId = serviceId;
  }

  public String getInvoiceReference() {
    return this.invoiceReference;
  }

  public boolean isSetInvoiceReference() {
    return (this.invoiceReference != null);
  }

  public void setInvoiceReference(String invoiceReference) {
    this.invoiceReference = invoiceReference;
  }

  public String getCustomerReference() {
    return customerReference;
  }

  public boolean isSetCustomerReference() {
    return (this.customerReference != null);
  }

  public void setCustomerReference(String customerReference) {
    this.customerReference = customerReference;
  }

  public Currency getCurrency() {
    return Currency.getInstance(this.currency);
  }

  public boolean isSetCurrency() {
    return (this.currency != null);
  }

  public void setCurrency(Currency dbCurrency) {
    this.currency = dbCurrency.getCurrencyCode();
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public Date getCreationDate() {
    if (this.creationDate == null)
      return null;
    return new Date(this.creationDate.getTime());
  }

  public boolean isSetCreationDate() {
    return (this.creationDate != null);
  }

  public void setCreationDate(Date creationDate) {
    if (creationDate != null)
      this.creationDate = new Date(creationDate.getTime());
    else
      this.creationDate = null;
  }

  public Date getDueDate() {
    if (this.dueDate == null)
      return null;
    return new Date(this.dueDate.getTime());
  }

  public boolean isSetDueDate() {
    return (this.dueDate != null);
  }

  public void setDueDate(Date dueDate) {
    if (dueDate != null)
      this.dueDate = new Date(dueDate.getTime());
    else
      this.dueDate = null;
  }

  public Date getPayDate() {
    if (this.payDate == null)
      return null;
    return new Date(this.payDate.getTime());
  }

  public boolean isSetPayDate() {
    return (this.payDate != null);
  }

  public void setPayDate(Date payDate) {
    if (payDate != null)
      this.payDate = new Date(payDate.getTime());
    else
      this.payDate = null;
  }

  public Date getExpirationDate() {
    if (this.expirationDate == null)
      return null;
    return new Date(this.expirationDate.getTime());
  }

  public boolean isSetExpirationDate() {
    return (this.expirationDate != null);
  }

  public void setExpirationDate(Date expirationDate) {
    if (expirationDate != null)
      this.expirationDate = new Date(expirationDate.getTime());
    else
      this.expirationDate = null;
  }

  public long getAmount() {
    return this.amount.longValue();
  }

  public boolean isSetAmount() {
    return (this.amount != null);
  }

  public void setAmount(long amount) {
    this.amount = Long.valueOf(amount);
  }

  public Long getCashBackAmount() {
    return cashBackAmount;
  }

  public void setCashBackAmount(Long cashBackAmount) {
    this.cashBackAmount = cashBackAmount;
  }

  public int getStatus() {
    return this.status.intValue();
  }

  public boolean isSetStatus() {
    return (this.status != null);
  }

  public void setStatus(int status) {
    this.status = Integer.valueOf(status);
  }

  public Set<InvoiceAttribute> getInvoiceAttributes() {
    return this.invoiceAttributes;
  }

  public boolean isSetInvoiceAttributes() {
    return (this.invoiceAttributes != null);
  }

  public void setInvoiceAttributes(Set<InvoiceAttribute> invoiceAttributes) {
    this.invoiceAttributes = invoiceAttributes;
  }

  public Boolean getIsPartialPaymentAllowed() {
    return isPartialPaymentAllowed;
  }

  public void setIsPartialPaymentAllowed(Boolean isPartialPaymentAllowed) {
    this.isPartialPaymentAllowed = isPartialPaymentAllowed;
  }
}
