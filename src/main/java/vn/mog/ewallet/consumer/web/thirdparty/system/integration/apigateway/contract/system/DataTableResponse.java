package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system;

import java.util.Collection;

public class DataTableResponse<T> {
  private Long draw;
  private Long recordsTotal;
  private Long recordsFiltered;
  private Collection<T> dataList;
  private Long totalAmount;
  private Long totalRealAmount;
  private Long totalCommission ;
  private Long totalCashBackAmount;

  public Long getDraw() {
    return draw;
  }

  public void setDraw(Long draw) {
    this.draw = draw;
  }

  public Long getRecordsTotal() {
    return recordsTotal;
  }

  public void setRecordsTotal(Long recordsTotal) {
    this.recordsTotal = recordsTotal;
  }

  public Long getRecordsFiltered() {
    return recordsFiltered;
  }

  public void setRecordsFiltered(Long recordsFiltered) {
    this.recordsFiltered = recordsFiltered;
  }

  public Collection<T> getDataList() {
    return dataList;
  }

  public void setDataList(Collection<T> dataList) {
    this.dataList = dataList;
  }

  public Long getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(Long totalAmount) {
    this.totalAmount = totalAmount;
  }

  public Long getTotalRealAmount() {
    return totalRealAmount;
  }

  public void setTotalRealAmount(Long totalRealAmount) {
    this.totalRealAmount = totalRealAmount;
  }

  public Long getTotalCommission() {
    return totalCommission;
  }

  public void setTotalCommission(Long totalCommission) {
    this.totalCommission = totalCommission;
  }

  public Long getTotalCashBackAmount() {
    return totalCashBackAmount;
  }

  public void setTotalCashBackAmount(Long totalCashBackAmount) {
    this.totalCashBackAmount = totalCashBackAmount;
  }
}
