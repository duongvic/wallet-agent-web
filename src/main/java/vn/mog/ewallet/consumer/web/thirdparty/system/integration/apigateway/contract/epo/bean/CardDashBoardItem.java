package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.bean;

import java.io.Serializable;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.CardStatus;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.CardType;

@SuppressWarnings("serial")
public class CardDashBoardItem implements Serializable {

  private CardProvider provider;
  private CardType cardType;
  private Integer faceValue;
  private CardStatus status;
  private Stage stage;
  private Long total;
  private Long capital;

  public CardProvider getProvider() {
    return provider;
  }

  public void setProvider(CardProvider provider) {
    this.provider = provider;
  }

  public CardType getCardType() {
    return cardType;
  }

  public void setCardType(CardType cardType) {
    this.cardType = cardType;
  }

  public Integer getFaceValue() {
    return faceValue;
  }

  public void setFaceValue(Integer faceValue) {
    this.faceValue = faceValue;
  }

  public CardStatus getStatus() {
    return status;
  }

  public void setStatus(CardStatus status) {
    this.status = status;
  }

  public Stage getStage() {
    return stage;
  }

  public void setStage(Stage stage) {
    this.stage = stage;
  }

  public Long getTotal() {
    return total;
  }

  public void setTotal(Long total) {
    this.total = total;
  }

  public Long getCapital() {
    return capital;
  }

  public void setCapital(Long capital) {
    this.capital = capital;
  }
}
