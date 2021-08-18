package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.card;

import java.util.List;
import vn.mog.framework.contract.base.MobiliserRequestType;

@SuppressWarnings("serial")
public class GetAvailableCardRequest extends MobiliserRequestType {

  private String payerCif ;

  private List<String> cardTypes;


  public String getPayerCif() {
    return payerCif;
  }

  public void setPayerCif(String payerCif) {
    this.payerCif = payerCif;
  }
  public List<String> getCardTypes() {
    return cardTypes;
  }

  public void setCardTypes(List<String> cardTypes) {
    this.cardTypes = cardTypes;
  }


  public GetAvailableCardRequest() {
  }

  public GetAvailableCardRequest(List<String> cardTypes) {
    this.setCardTypes(cardTypes);
  }

  public GetAvailableCardRequest(List<String> cardTypes, String payerCif ) {
    this.setCardTypes(cardTypes);
   this.setPayerCif(payerCif);

  }
}
