package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo;

import java.util.Collection;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.bean.CardItem;
import vn.mog.framework.contract.base.MobiliserResponseType;

@SuppressWarnings("serial")
public class FindCardResponse extends MobiliserResponseType {
  private Collection<CardItem> cards;
  private Long count;
  private Long amount;
  private Long capital;

  public Collection<CardItem> getCards() {
    return cards;
  }

  public void setCards(Collection<CardItem> cards) {
    this.cards = cards;
  }

  public Long getCount() {
    return count;
  }

  public void setCount(Long count) {
    this.count = count;
  }

  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public Long getCapital() {
    return capital;
  }

  public void setCapital(Long capital) {
    this.capital = capital;
  }
}
