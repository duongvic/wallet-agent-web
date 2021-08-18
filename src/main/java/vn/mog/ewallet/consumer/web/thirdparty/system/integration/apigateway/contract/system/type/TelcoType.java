package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type;

public enum TelcoType {

  VTM("VTM", "VIETTEL"),
  VMS("VMS", "MOBIFONE"),
  VNP("VNP", "VINAPHONE"),
  VNM("VNM", "VNMOBILE"),
  GMOBILE("GMOBILE", "GMOBILE"), // TelCard

  VCOIN("VCOIN", "VCOIN"),
  GATE("GATE", "GATE"),
  ZING("ZING", "ZING"),
  GARENA("GARENA", "GARENA"),
  ONCASH("ONCASH", "ONCASH"),
  MGC("MGC", "MEGACARD"),
  BIT("BIT", "BIT"),
  VCARD("VCARD", "VCARD"),
  VINPLAY("VINPLAY", "VINPLAY"), // GameCard

  DATA_VINAPHONE("DT_VNP", "DATA_VINAPHONE"), //vinaPhone
  DATA_MOBIFONE("DT_VMS", "DATA_MOBIFONE"), // DataCode mobiPhone
  DATA_VIETTEL("DT_VTM", "DATA_VIETTEL"),//viettel3G

  SCOIN("SCOIN","SCOIN"),
  SOHA("SOHA","SOHA"),
  FUNCARD("FUNCARD","FUNCARD"),

  APPOTA("APPOTA","APPOTA"),
  ANPAY("ANPAY","ANPAY");

  public String code;
  public String name;

  private TelcoType(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public static TelcoType getTelcoType(String label) {
    for (TelcoType telcoType : TelcoType.values()) {
      if (telcoType.name().equalsIgnoreCase(label) || telcoType.code.equalsIgnoreCase(label)
          || telcoType.name.equalsIgnoreCase(label)) {
        return telcoType;
      }
    }
    return null;
  }

  public String getCode() {
    return code;
  }

  public String getName() {
    return name;
  }

}
