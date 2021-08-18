package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type;


public enum CardType {

  VIETTEL("VTM", "VIETTEL", "040101"),
  VINAPHONE("VNP", "VINAPHONE", "040102"),
  MOBIFONE("VMS", "MOBIFONE", "040103"),
  GMOBILE("GMOBILE", "GMOBILE", "040104"),
  VNMOBILE("VNM", "VNMOBILE", "040105"), // TelCard

  ZING("ZING", "ZING", "040106"),
  GATE("GATE", "GATE", "040107"),
  VCOIN("VCOIN", "VCOIN", "040108"),
  GARENA("GARENA", "GARENA", "040109"),
  VINPLAY("VINPLAY", "VINPLAY", "040110"),
  ONCASH("ONCASH", "ONCASH", "040111"),
  BIT("BIT", "BIT", "040112"),
  APPOTA("APPOTA","APPOTA", "040115"),
  SOHA("SOHA","SOHA", "040119"),
  FUNCARD("FUNCARD","FUNCARD", "040120"),
  SCOIN("SCOIN","SCOIN", "040121"),
  ANPAY("ANPAY","ANPAY", "040126"), // GameCard

  VIETTEL_EXPORT("VTM", "VIETTEL", "070101"),
  VINAPHONE_EXPORT("VNP", "VINAPHONE", "070102"),
  MOBIFONE_EXPORT("VMS", "MOBIFONE", "070103"),
  GMOBILE_EXPORT("GMOBILE", "GMOBILE", "070104"),
  VNMOBILE_EXPORT("VNM", "VNMOBILE", "070105"),
  ZING_EXPORT("ZING", "ZING", "070106"),
  GATE_EXPORT("GATE", "GATE", "070107"),
  VCOIN_EXPORT("VCOIN", "VCOIN", "070108"),
  GARENA_EXPORT("GARENA", "GARENA", "070109"),
  VINPLAY_EXPORT("VINPLAY", "VINPLAY", "070110"),
  ONCASH_EXPORT("ONCASH", "ONCASH", "070111"), // BatchCard

  DATA_VINAPHONE("DT_VNP", "DATA_VINAPHONE", "070112"), //vinaPhone 3G
  DATA_MOBIFONE("DT_VMS", "DATA_MOBIFONE", "040117"), // mobiPhone 3G
  DATA_VIETTEL("DT_VTM", "DATA_VIETTEL", "040118"); //viettel 3G

  public String code;
  public String name;
  private String serviceCode;

  CardType(String code, String name, String serviceCode) {
    this.code = code;
    this.name = name;
    this.serviceCode = serviceCode;
  }

  public static CardType getCardType(String label) {
    for (CardType cardType : CardType.values()) {
      if (cardType.name().equalsIgnoreCase(label)
          || cardType.code.equalsIgnoreCase(label)
          || cardType.name.equalsIgnoreCase(label)
          || cardType.serviceCode.equalsIgnoreCase(label)) {
        return cardType;
      }
    }
    return null;
  }

  public String getName() {
    return name;
  }

  public String getCode() {
    return code;
  }

  public String getServiceCode() {
    return serviceCode;
  }
}
