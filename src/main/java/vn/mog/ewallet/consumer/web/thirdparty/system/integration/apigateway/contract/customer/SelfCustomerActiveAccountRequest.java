package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SelfCustomerActiveAccountRequest extends SelfCustomerActiveAccountRequestType {

  /**
   *
   * "blacklist_reason_type_id (Integer) // Active(0),WrongPWOverTimes(1), DeviceLost(2),PaymentDispute(3),FraudSuspicion(4),RegistrationPending(5)
   * ---
   * Active - Mở khóa: 0
   * Inactive - Khóa: != 0"
   * */

  @JsonProperty("blacklist_reason_type_id")
  private Long blackListReasonTypeId;

  public Long getBlackListReasonTypeId() {
    return blackListReasonTypeId;
  }

  public void setBlackListReasonTypeId(Long blackListReasonTypeId) {
    this.blackListReasonTypeId = blackListReasonTypeId;
  }
}
