package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans;

public enum FundOrderFlowStage {

  INIT(0, "Declared"), 
  SALE_EXCUTIVE_REJECTED (1, "Sale excutive rejected"), 
  SALE_EXCUTIVE_READY_TO_VERIFY(2, "Sale excutive ready to verify"), 
  FINANCE_SUPPORT_REJECTED(3, "Finance support rejected"), 
  FINANCE_SUPPORT_READY_TO_VERIFY(4, "Finance support ready to verify"), 
  FINANCE_MANAGER_REJECTED(5, "Finance manager rejected"),
  FINANCE_MANAGER_READY_TO_VERIFY(6, "Finance manager ready to verify"), 
  FINISHED(8, "Finished");

  public int code;
  public String displayText;

  private FundOrderFlowStage(int value, String displayText) {
    this.code = value;
    this.displayText = displayText;
  }

  public int value() {
    return this.code;
  }

  public String displayText() {
    return this.displayText;
  }

  public static FundOrderFlowStage getWorkFlowState(int value) {
    for (FundOrderFlowStage state : FundOrderFlowStage.values()) {
      if (state.code == value) {
        return state;
      }
    }
    return null;
  }
}
