package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type;

public enum WaterBillPaymentType {

  WATER_SERVICE_CODE_TRUNG_AN("05011603", "Công ty nước Trung An-TP.HCM"),
  WATER_SERVICE_CODE_NHA_BE("05011608", "Công ty Cổ phần Cấp nước Nhà Bè"),
  WATER_SERVICE_CODE_DONG_NAI("05011600", "Công ty nước Đồng Nai"),
  WATER_SERVICE_CODE_QUANG_NAM("05011601", "Công ty nước Quảng Nam"),
  WATER_SERVICE_CODE_TP_HCM("05011602", "Công ty nước TP.Hồ Chí Minh"),
  WATER_SERVICE_CODE_VINH_PHUC("05011605", "Công ty nước tỉnh Vĩnh Phúc"),
  WATER_SERVICE_CODE_BARIA_VUNGTAU("05011612", "Công ty nước tỉnh Bà Rịa - Vũng Tàu"),
  WATER_SERVICE_CODE_DVXD_DONGNAI("05011613", "Cty CP DVXD cấp nước Đồng Nai"),
  WATER_SERVICE_CODE_NHON_TRACH("05011614", "Cty CP Cấp nước Nhơn Trạch"),
  WATER_SERVICE_CODE_BINH_DUONG("05011615", "Công ty nước Bình Dương"),
  WATER_SERVICE_CODE_CAN_THO("05011616", "Công ty nước tỉnh Cần Thơ"),

  WATER_SERVICE_CODE_BEN_THANH("05011609", "Nước Bến Thành"),
  WATER_SERVICE_CODE_CHO_LON("05011604", "Nước Chợ Lớn"),
  WATER_SERVICE_CODE_DA_NANG("05011619", "Nước Đà Nẵng"),
  WATER_SERVICE_CODE_HAI_PHONG("05011620", "Nước Hải Phòng"),
  WATER_SERVICE_CODE_GIA_DINH("05011611", "Nước Gia Định"),
  WATER_SERVICE_CODE_TAN_HOA("05011606", "Nước Tân Hòa"),
  WATER_SERVICE_CODE_HUE("05011618", "Nước Huế"),
  WATER_SERVICE_CODE_PHU_HOA_TAN("05011607", "Nước Phú Hòa Tân"),
  WATER_SERVICE_CODE_THU_DUC("05011610", "Nước Thủ Đức"),
  WATER_SERVICE_CODE_XI_NGHIEP_CAP_NUOC_SH_NT("05011617", "Nước Xí Nghiệp Cấp Nước Sinh Hoạt Nông Thôn"),
  WATER_SERVICE_CODE_LONG_KHANH("05011621", "Nước Long Khánh"),

  WATER_SERVICE_CODE_CAO_BANG("05011622", "Nước Cao Bằng"),
  WATER_SERVICE_CODE_VIWACO("05011623", "Nước Công ty cổ phần Viwaco"),
  WATER_SERVICE_CODE_SO3_HN("05011624", "Nước số 3 Hà Nội"),
  WATER_SERVICE_CODE_HA_NAM("05011625", "Nước Hà Nam"),
  WATER_SERVICE_CODE_SO2_HP("05011626", "Nước số 2 Hải Phòng"),
  WATER_SERVICE_CODE_CAN_THO_2("05011627", "Nước Cần Thơ 2"),
  WATER_SERVICE_CODE_DONG_THAP("05011628", "Nước Đồng Tháp"),
  WATER_SERVICE_CODE_PHU_MY("05011629", "Nước Phú Mỹ"),
  WATER_SERVICE_CODE_SON_LA("05011630", "Nước Sơn La");

  private String code;
  private String name;

  WaterBillPaymentType(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public String getName() {
    return name;
  }

  public static String getWaterBillPaymentType(String label) {
    for (WaterBillPaymentType item : WaterBillPaymentType.values()) {
      if (item.name().equalsIgnoreCase(label) || item.code.equalsIgnoreCase(label)
          || item.name.equalsIgnoreCase(label)) {
        return String.valueOf(item.name);
      }
    }
    return null;
  }
}
