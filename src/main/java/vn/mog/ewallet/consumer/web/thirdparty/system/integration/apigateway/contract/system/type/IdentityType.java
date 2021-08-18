package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type;


public enum IdentityType {
  Generic(0, "Generic"),
  CitizenshipCard(1, "CitizenshipCard"), // cmt
  IdentityCard(2, "IdentityCard"), // old cmd
  ForeignerCard(3, "ForeignerCard"),
  PersonalTaxIdNumber(4, "PersonalTaxIdNumber"),
  BusinessTaxIdNumber(5, "BusinessTaxIdNumber"),
  IdependentPatrimony(6, "IdependentPatrimony"),
  Passport(7, "Passport"), // passport
  Correspondent(8, "Correspondent"),
  ForeignerTaxIdNumber(9, "ForeignerTaxIdNumber"),
  ImageProfile(10, "ImageProfile");

  public String name;
  public int code;

  IdentityType(int code, String name) {
    this.code = code;
    this.name = name;
  }

  public static IdentityType getIdentityType(String label) {
    for (IdentityType item : IdentityType.values()) {
      if (item.name.equalsIgnoreCase(label) || String.valueOf(item.code).equalsIgnoreCase(label))
        return item;
    }
    return null;
  }
}
