package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.card;


import java.util.Map;
import vn.mog.framework.contract.base.MobiliserResponseType;

@SuppressWarnings("serial")
public class GetAvailableCardResponse extends MobiliserResponseType {

  private Map<String, Map<Integer, Long>> availableCard;

  public GetAvailableCardResponse() {
  }

  public Map<String, Map<Integer, Long>> getAvailableCard() {
    return availableCard;
  }

  public void setAvailableCard(Map<String, Map<Integer, Long>> availableCard) {
    this.availableCard = availableCard;
  }
}
