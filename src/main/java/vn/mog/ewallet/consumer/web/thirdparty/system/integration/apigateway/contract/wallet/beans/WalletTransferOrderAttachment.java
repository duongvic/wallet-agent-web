package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.beans;

import java.io.Serializable;
import java.util.Date;

public class WalletTransferOrderAttachment implements Serializable {

  private static final long serialVersionUID = 1L;
  /*    */
  private Date createdTime;
  private Long creatorId;
  private Date lastUpdatedTime;
  private Long lastUpdaterId;
  /*    */
  private Long attachmentId;
  private Long walletTransferOrderId;
  private String name;
  private String contentType;
  private byte[] content;
  private String imageBase64;

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

  public Long getAttachmentId() {
    return attachmentId;
  }

  public void setAttachmentId(Long attachmentId) {
    this.attachmentId = attachmentId;
  }

  public Long getWalletTransferOrderId() {
    return walletTransferOrderId;
  }

  public void setWalletTransferOrderId(Long walletTransferOrderId) {
    this.walletTransferOrderId = walletTransferOrderId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public byte[] getContent() {
    return content;
  }

  public void setContent(byte[] content) {
    this.content = content;
  }

  public String getImageBase64() {
    return imageBase64;
  }

  public void setImageBase64(String imageBase64) {
    this.imageBase64 = imageBase64;
  }
}
