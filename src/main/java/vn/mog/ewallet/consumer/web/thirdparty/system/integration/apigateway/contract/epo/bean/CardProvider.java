package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.bean;

public enum CardProvider {
  //  VIETTEL(1, "Viettel Telecom"),
//  VNPAY(2, "VnPay JSC"),
//  VNTP(3, "Vinatopup"),
//  EPAY(4, "EPAY JSC"),
//  VTC(5, "VTC Intecom"),
//  ESALE(6, "ESALE"),
//  ZOTA(8, "ZO-TA JSC"),
//  MOBIFONE(9, "Mobifone"),
//  VINPLAY(10, "Vinplay"),
//  TPT(11, "TP Telecom"),
//  VNPT(12, "VNPT Group"),
//  VMG(13,"VMG");
//
//  // Why is CardProvider frontend different from CardProvider backend ?
//
//  public int code;
//  public String name;
//
//  private CardProvider(int code, String name) {
//    this.code = code;
//    this.name = name;
//  }
//
//  public String getName() {
//    return name;
//  }
//
//  public Integer getCode() {
//    return code;
//  }

  VIETTEL("VIETTEL", "Viettel Telecom"),
  VNPAY("VNPAY", "VnPay JSC"),
  VNTP("VNTP", "Vinatopup"),
  EPAY("EPAY", "EPAY JSC"),
  VTC("VTC", "VTC Intecom"),
  ESALE("ESALE", "ESALE"),
  ZOTA("ZOTA", "ZO-TA JSC"),
  MOBIFONE("MOBIFONE", "Mobifone"),
  VINPLAY("VINPLAY", "Vinplay"),
  ZOPAY("ZOPAY", "ZOPAY"),
  APPOTA("APPOTA", "Appota"),
  VPAY("VPAY", "VPAY"),
  THANHSON("THANHSON", "Thanh Sơn"),
  GARENA("GARENA", "Garena"),
  IMEDIA("IMEDIA", "IMEDIA"),
  IOMEDIA("IOMEDIA", "IOMEDIA");

  private String code;
  private String name;

  CardProvider(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public static CardProvider getByCode(String code) {
    for (CardProvider item : CardProvider.values()) {
      if (code.equalsIgnoreCase(String.valueOf(item.code))) {
        return item;
      }
    }
    return null;
  }

  public static void main(String[] a) {
    CardProvider p = CardProvider.ZOTA;
    System.out.println(p.code + p.name + p.name());

    System.out.println(CardProvider.valueOf("VIETTEL").code);
  }
}
