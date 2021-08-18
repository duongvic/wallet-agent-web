package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type;

import vn.mog.ewallet.consumer.web.util.tools.NumberUtil;


public enum FaceValueType {
  TEN(10000, "10,000"),
  TWENTY(20000, "20,000"),
  THIRTY(30000, "30,000"),
  FIFTY(50000, "50,000"),
  HUNDRED(100000, "100,000"),
  TWO_HUNDRED(200000, "200,000"),
  THREE_HUNDRED(300000, "300,000"),
  FIVE_HUNDRED(500000, "500,000"),
  ONE_THOUSAND(1000000, "1,000,000"),
  TWO_THOUSAND(2000000, "2,000,000"),
  FIVE_THOUSAND(5000000, "5,000,000"),

  ONE(1000, "1,000"),
  TWO(2000, "2,000"),
  THREE(3000, "3,000"),
  FIVE(5000, "5,000"),
  FOURTEEN(14000, "14,000"),
  TWENTY_EIGHTH(28000, "28,000"),
  FORTY_SECOND(42000, "42,000"),
  FIFTY_SIX(56000, "56,000"),
  EIGHTY_FOUR(84000, "84,000"),
  SEVENTY(70000, "70,000"),
  ONE_HUNDRED_TWENTY(120000, "120,000"),
  ONE_HUNDRED_FIFTY(150000, "150,000");

  public int code;
  public String name;

  FaceValueType(int code, String name) {
    this.code = code;
    this.name = name;
  }

  public static boolean existFaceValue(int label) {
    for (FaceValueType item : FaceValueType.values()) {
      if ((item.getCode() == label)) {
        return true;
      }
    }
    return false;
  }

  public static FaceValueType getFaceValueType(String label) {
    for (FaceValueType item : FaceValueType.values()) {
      if (item.getCode() == NumberUtil.convertToInt(label)) {
        return item;
      }
    }
    return null;
  }

  public String getName() {
    return name;
  }

  public Integer getCode() {
    return code;
  }
}
