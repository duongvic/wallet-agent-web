package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class CreateBankItemResponse extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  private Long bankItemId;

  public Long getBankItemId() {
    return bankItemId;
  }

  public void setBankItemId(Long bankItemId) {
    this.bankItemId = bankItemId;
  }
}
