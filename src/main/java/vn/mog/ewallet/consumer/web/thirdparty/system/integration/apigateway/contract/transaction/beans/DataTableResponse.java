package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans;

import java.util.Collection;

public class DataTableResponse<T> {
  private Long draw;
  private Long recordsTotal;
  private Long recordsFiltered;
  private Collection<T> dataList;

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
}
