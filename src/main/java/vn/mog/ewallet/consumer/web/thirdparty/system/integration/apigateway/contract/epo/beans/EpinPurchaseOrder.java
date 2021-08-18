package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import vn.mog.ewallet.consumer.web.controller.epo.beans.EpinPurchaseOrderFlowStage;

public class EpinPurchaseOrder implements Serializable {

  private static final long serialVersionUID = 1L;
  private Long purchaseOrderId;
  private String poCode;
  private String merchantName;
  private String keyHolder;
  private Long totalValue;
  private Long totalCommission;
  private Long totalFee;
  private Long totalQuantity;
  private Long capitalValue;
  private Integer status;
  private Integer stage;
  private String storeType;
  private String info;
  private List<EpinPurchaseOrderDetail> purchaseOrderDetails;

  private Long preBalance;
  private Long postBalance;

  private Date createdTime;
  private Long creatorId;
  private Date lastUpdatedTime;
  private Long lastUpdaterId;

  public EpinPurchaseOrder() {
    super();
  }

  public EpinPurchaseOrder(Long purchaseOrderId, Long totalValue, Long totalQuantity,
      List<EpinPurchaseOrderDetail> purchaseOrderDetails) {
    this.purchaseOrderId = purchaseOrderId;
    this.totalValue = totalValue;
    this.totalQuantity = totalQuantity;
    this.purchaseOrderDetails = purchaseOrderDetails;
  }

  public EpinPurchaseOrder(Long id, String mpoCode, String merchantName, String keyHolder,
      Long totalValue, Long totalCommission, Long totalFee, Long totalQuantity, Integer status,
      List<EpinPurchaseOrderDetail> purchaseOrderDetails, Date createdTime, Long creatorId,
      Date lastUpdatedTime, Long capitalValue, Long lastUpdaterId) {
    super();
    this.setPurchaseOrderId(getPurchaseOrderId());
    this.setPoCode(getPoCode());
    this.merchantName = merchantName;
    this.keyHolder = keyHolder;
    this.totalValue = totalValue;
    this.totalCommission = totalCommission;
    this.totalFee = totalFee;
    this.totalQuantity = totalQuantity;
    this.capitalValue = capitalValue;
    this.status = status;
    this.purchaseOrderDetails = purchaseOrderDetails;
    this.createdTime = createdTime;
    this.creatorId = creatorId;
    this.lastUpdatedTime = lastUpdatedTime;
    this.lastUpdaterId = lastUpdaterId;
  }

  public EpinPurchaseOrder(List<EpinPurchaseOrderDetail> epinDetails, Long totalMoney, Long totalQuantity , String keyHolder) {
    this.purchaseOrderDetails = epinDetails;
    this.totalValue = totalMoney;
    this.totalQuantity = totalQuantity;
    this.keyHolder = keyHolder;
  }

  public String getMerchantName() {
    return merchantName;
  }

  public void setMerchantName(String merchantName) {
    this.merchantName = merchantName;
  }

  public String getKeyHolder() {
    return keyHolder;
  }

  public void setKeyHolder(String keyHolder) {
    this.keyHolder = keyHolder;
  }

  public Long getTotalValue() {
    return totalValue;
  }

  public void setTotalValue(Long totalValue) {
    this.totalValue = totalValue;
  }

  public Long getTotalCommission() {
    return totalCommission;
  }

  public void setTotalCommission(Long totalCommission) {
    this.totalCommission = totalCommission;
  }

  public Long getTotalFee() {
    return totalFee;
  }

  public void setTotalFee(Long totalFee) {
    this.totalFee = totalFee;
  }

  public Long getTotalQuantity() {
    return totalQuantity;
  }

  public void setTotalQuantity(Long totalQuantity) {
    this.totalQuantity = totalQuantity;
  }

  public Long getCapitalValue() {
    return capitalValue;
  }

  public void setCapitalValue(Long capitalValue) {
    this.capitalValue = capitalValue;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public List<EpinPurchaseOrderDetail> getPurchaseOrderDetails() {
    return purchaseOrderDetails;
  }

  public void setPurchaseOrderDetails(List<EpinPurchaseOrderDetail> purchaseOrderDetails) {
    this.purchaseOrderDetails = purchaseOrderDetails;
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

  public String getPoCode() {
    return poCode;
  }

  public void setPoCode(String poCode) {
    this.poCode = poCode;
  }

  public Long getPurchaseOrderId() {
    return purchaseOrderId;
  }

  public void setPurchaseOrderId(Long purchaseOrderId) {
    this.purchaseOrderId = purchaseOrderId;
  }

  public Integer getStage() {
    return stage;
  }

  public void setStage(Integer stage) {
    this.stage = stage;
  }

  public String getStoreType() {
    return storeType;
  }

  public void setStoreType(String storeType) {
    this.storeType = storeType;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public String getLabelState() {
    try {
      return EpinPurchaseOrderFlowStage.getWorkFlowState(stage).displayText;
    } catch (Exception e) {
      return StringUtils.EMPTY;
    }
  }

  public enum StoreType {

    OFFLINE_STORE, ONLINE_STORE, ONLINE_STORE_N02;

    public static StoreType getByName(String name) {
      for (StoreType item : StoreType.values()) {
        if (name.equals(item.name())) {
          return item;
        }
      }
      return null;
    }
  }

}
