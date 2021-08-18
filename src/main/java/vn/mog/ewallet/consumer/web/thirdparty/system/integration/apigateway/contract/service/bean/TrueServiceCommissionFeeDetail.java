package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.service.bean;

import java.io.Serializable;

public class TrueServiceCommissionFeeDetail implements Serializable {

  private static final long serialVersionUID = 1L;

  private Double commision;

  private Double fee;

  private Long commisionFixedAmount;

  private Long feeFixedAmount;

  public TrueServiceCommissionFeeDetail() {
    super();
    this.commision = 0.0D;
    this.fee = 0.0D;
    this.commisionFixedAmount = 0L;
    this.feeFixedAmount = 0L;
  }

  public Double getCommision() {
    return commision;
  }

  public void setCommision(Double commision) {
    this.commision = commision;
  }

  public Double getFee() {
    return fee;
  }

  public void setFee(Double fee) {
    this.fee = fee;
  }

  public Long getCommisionFixedAmount() {
    return commisionFixedAmount;
  }

  public void setCommisionFixedAmount(Long commisionFixedAmount) {
    this.commisionFixedAmount = commisionFixedAmount;
  }

  public Long getFeeFixedAmount() {
    return feeFixedAmount;
  }

  public void setFeeFixedAmount(Long feeFixedAmount) {
    this.feeFixedAmount = feeFixedAmount;
  }
}
