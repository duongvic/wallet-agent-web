package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo;

import java.util.List;
import java.util.Map;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.bean.CardDashBoardItem;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.bean.CardDashBoardItemTable;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.bean.PieChartItem;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.bean.StoreNotfInfo;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetCustomerCardDashboardResponse extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  private List<PieChartItem> lineMap;
  private List<PieChartItem> pieProvider;
  private List<PieChartItem> pieCardType;
  private List<PieChartItem> pieFaceValue;

  private List<CardDashBoardItem> cardDashBoardItems;
  private List<CardDashBoardItemTable> cardDashBoardItemsTable;

  private Map<String, Map<Integer, StoreNotfInfo>> warningLevelResult;

  public List<PieChartItem> getLineMap() {
    return lineMap;
  }

  public void setLineMap(
      List<PieChartItem> lineMap) {
    this.lineMap = lineMap;
  }

  public List<PieChartItem> getPieProvider() {
    return pieProvider;
  }

  public void setPieProvider(
      List<PieChartItem> pieProvider) {
    this.pieProvider = pieProvider;
  }

  public List<PieChartItem> getPieCardType() {
    return pieCardType;
  }

  public void setPieCardType(
      List<PieChartItem> pieCardType) {
    this.pieCardType = pieCardType;
  }

  public List<PieChartItem> getPieFaceValue() {
    return pieFaceValue;
  }

  public void setPieFaceValue(
      List<PieChartItem> pieFaceValue) {
    this.pieFaceValue = pieFaceValue;
  }

  public List<CardDashBoardItem> getCardDashBoardItems() {
    return cardDashBoardItems;
  }

  public void setCardDashBoardItems(
      List<CardDashBoardItem> cardDashBoardItems) {
    this.cardDashBoardItems = cardDashBoardItems;
  }

  public List<CardDashBoardItemTable> getCardDashBoardItemsTable() {
    return cardDashBoardItemsTable;
  }

  public void setCardDashBoardItemsTable(
      List<CardDashBoardItemTable> cardDashBoardItemsTable) {
    this.cardDashBoardItemsTable = cardDashBoardItemsTable;
  }

  public Map<String, Map<Integer, StoreNotfInfo>> getWarningLevelResult() {
    return warningLevelResult;
  }

  public void setWarningLevelResult(
      Map<String, Map<Integer, StoreNotfInfo>> warningLevelResult) {
    this.warningLevelResult = warningLevelResult;
  }
}
