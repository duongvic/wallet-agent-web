package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.bean;

import java.io.Serializable;
import java.util.Date;

public class WalletTransferOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    /*    */
    private Date createdTime;
    private Long creatorId;
    private Date lastUpdatedTime;
    private Long lastUpdaterId;
    /*    */

    private Long id;
    private Long refTxnId;
    private Integer refTxnStatus;

    private String serviceType;
    private Long serviceId;
    private String serviceCode;

    private Long payerId;
    private String payerCif;
    private String payerUsername;

    private Long payeeId;
    private String payeeCif;
    private String payeeUsername;
    private String payeePhoneNumber;
    
    private String creatorUsername;
    private String approverUsername;

    private String terminalId;

    private String info;
    private Long amount;
    private Integer stage;

    public Date getCreatedTime() {
	return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
	this.createdTime = createdTime;
    }

    public Long getCreatorId() {
	return creatorId;
    }

    public void setCreatorId(Long creatorId) {
	this.creatorId = creatorId;
    }

    public Date getLastUpdatedTime() {
	return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
	this.lastUpdatedTime = lastUpdatedTime;
    }

    public Long getLastUpdaterId() {
	return lastUpdaterId;
    }

    public void setLastUpdaterId(Long lastUpdaterId) {
	this.lastUpdaterId = lastUpdaterId;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Long getRefTxnId() {
	return refTxnId;
    }

    public void setRefTxnId(Long refTxnId) {
	this.refTxnId = refTxnId;
    }

    public Integer getRefTxnStatus() {
	return refTxnStatus;
    }

    public void setRefTxnStatus(Integer refTxnStatus) {
	this.refTxnStatus = refTxnStatus;
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

    public Long getPayerId() {
	return payerId;
    }

    public void setPayerId(Long payerId) {
	this.payerId = payerId;
    }

    public String getPayerCif() {
	return payerCif;
    }

    public void setPayerCif(String payerCif) {
	this.payerCif = payerCif;
    }

    public String getPayerUsername() {
	return payerUsername;
    }

    public void setPayerUsername(String payerUsername) {
	this.payerUsername = payerUsername;
    }

    public Long getPayeeId() {
	return payeeId;
    }

    public void setPayeeId(Long payeeId) {
	this.payeeId = payeeId;
    }

    public String getPayeeCif() {
	return payeeCif;
    }

    public void setPayeeCif(String payeeCif) {
	this.payeeCif = payeeCif;
    }

    public String getPayeePhoneNumber() {
      return payeePhoneNumber;
    }

    public void setPayeePhoneNumber(String payeePhoneNumber) {
      this.payeePhoneNumber = payeePhoneNumber;
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

    public String getTerminalId() {
	return terminalId;
    }

    public void setTerminalId(String terminalId) {
	this.terminalId = terminalId;
    }

    public String getInfo() {
	return info;
    }

    public void setInfo(String info) {
	this.info = info;
    }

    public Long getAmount() {
	return amount;
    }

    public void setAmount(Long amount) {
	this.amount = amount;
    }

    public Integer getStage() {
	return stage;
    }

    public void setStage(Integer stage) {
	this.stage = stage;
    }

}
