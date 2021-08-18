package vn.mog.ewallet.consumer.web.util.tools;

import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.SESSION_ACCOUNT_LOGIN;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import vn.mog.ewallet.consumer.web.contract.UserLogin;

public class PhoneNumberUtil {

  public static final String DEFAULT_COUNTRY_CODE = "84";
  private static final List<String> PREFIXES_SIZE2 = Arrays.asList(new String[]{"20", "27", "30",
      "31", "32", "33", "34", "36", "39", "40", "41", "43", "44", "45", "46", "47", "48", "49",
      "51", "52", "53", "54", "55", "56", "57", "58", "60", "61", "62", "63", "64", "65", "66",
      "81", "82", "83", "84", "86", "90", "91", "92", "93", "94", "95", "98", "37", "38", "42"});

  //"\u2605"
  public static final String STAR = "\u2605";

  //matches numbers only
  private static final Pattern regexOnlyNumbers = Pattern.compile("^[0-9]*$");

  //matches 10-digit numbers only
  private static final Pattern regexTenNumbers = Pattern.compile("^[0-9]{10}$");

  //matches 11-digit numbers only
  private static final Pattern regexEleventNumbers = Pattern.compile("^[0-9]{10}$");

  private static final Pattern regexMobilePhoneVn = Pattern.compile("^(01[2689]|09)[0-9]{8}$");

  private final String msisdn;

  private static Map<String, String> cardHolderMap;

  public PhoneNumberUtil(String msisdn, String countryCode) {
    String trimmedMsisdn = msisdn.replaceAll("[^0-9]", "");

    if (("1".equals(countryCode)) && (trimmedMsisdn.length() == 10)) {
      this.msisdn = "+1" + trimmedMsisdn;
    } else if (trimmedMsisdn.startsWith("0000")) {
      this.msisdn = "+" + countryCode + trimmedMsisdn.substring(1);
    } else if (trimmedMsisdn.startsWith("000")) {
      this.msisdn = "+" + countryCode + trimmedMsisdn;
    } else if (trimmedMsisdn.startsWith("00")) {
      this.msisdn = "+" + trimmedMsisdn.substring(2);
    } else if (trimmedMsisdn.startsWith("0")) {
      this.msisdn = "+" + countryCode + trimmedMsisdn.substring(1);
    } else {
      this.msisdn = "+" + trimmedMsisdn;
    }
  }

  public PhoneNumberUtil(String msisdn) {
    this(msisdn, DEFAULT_COUNTRY_CODE);
  }

  public String getInternationalFormat() {
    return this.msisdn;
  }

  public String getNumericInternationalFormat() {
    return "00" + this.msisdn.substring(1);
  }

  public String getShortInternationalFormat() {
    return this.msisdn.substring(1);
  }

  public String getNationalFormat() {
    int chars = 3;

    if ((this.msisdn.charAt(1) == '1') || (this.msisdn.charAt(1) == '7')) {
      chars = 1;
    }
    if (PREFIXES_SIZE2.contains(this.msisdn.substring(1, 3))) {
      chars = 2;
    }
    return "0" + this.msisdn.substring(chars + 1);
  }

  public String toString() {
    return getInternationalFormat();
  }

  public boolean equals(Object o) {
    if (!(o instanceof PhoneNumberUtil)) {
      return false;
    }
    return getInternationalFormat().equals(((PhoneNumberUtil) o).getInternationalFormat());
  }

  public int hashCode() {
    return getInternationalFormat().hashCode();
  }

  public static String getCardHolderByPhoneNumber(String phoneNumber) throws Exception {
    if (StringUtils.isEmpty(phoneNumber)) {
      return StringUtils.EMPTY;
    }
    String key;
    if (11 <= phoneNumber.length()) {
      key = phoneNumber.substring(0, 4);
    } else {
      key = phoneNumber.substring(0, 3);
    }
    if (cardHolderMap == null) {
      cardHolderMap = new HashMap<>();
      String[] viettel;
      String[] vinaphone;
      String[] mobifone;
      String[] vietnammobile;
      String[] gmobile;

      viettel = new String[]{"032", "033", "034", "035", "036", "037", "038", "039", "086", "098",
          "097", "096", "0169", "0168", "0167", "0166", "0165", "0164", "0163", "0162"};
      vinaphone = new String[]{"081", "082", "083", "084", "085", "091", "094", "0123", "0124",
          "0125", "0127", "0129", "088"};
      mobifone = new String[]{"070", "076", "077", "078", "079", "090", "093", "0122", "0126",
          "0128", "0121", "0120", "089"};
      vietnammobile = new String[]{"056", "058", "092", "0188", "0186"};
      gmobile = new String[]{"059", "099", "0199"};

      for (String keyNumber : viettel) {
        cardHolderMap.put(keyNumber, "Viettel");
      }
      for (String keyNumber : vinaphone) {
        cardHolderMap.put(keyNumber, "Vinaphone");
      }
      for (String keyNumber : mobifone) {
        cardHolderMap.put(keyNumber, "MobiFone");
      }
      for (String keyNumber : vietnammobile) {
        cardHolderMap.put(keyNumber, "Vietnamobile");
      }
      for (String keyNumber : gmobile) {
        cardHolderMap.put(keyNumber, "GMobile");
      }
    }

    if (cardHolderMap.containsKey(key)) {
      return cardHolderMap.get(key);
    }

    return null;
  }

  public static String getPinCodeServiceIdByPhoneNumber(String phoneNumber) throws Exception {
    UserLogin userLogin = (UserLogin) SessionUtil.getAttribute(SESSION_ACCOUNT_LOGIN);
    if (StringUtils.isBlank(phoneNumber) && userLogin != null) {
      phoneNumber = userLogin.getPhoneNumber();
    }
    String productName = PhoneNumberUtil.getCardHolderByPhoneNumber(phoneNumber);
    return PhoneNumberUtil.getPinCodeServiceIdByProductName(productName);
  }

  public static String getTopUpServiceIdByProductName(String productName) throws Exception {
    switch (productName) {
      case "MobiFone":
        return "060103";
      case "Vinaphone":
        return "060102";
      case "GMobile":
        return "060104";
      case "Vietnamobile":
        return "060105";
      default:
        return "060101";
    }
  }

  public static String getPinCodeServiceIdByProductName(String productName) throws Exception {
    switch (productName) {
      case "Viettel":
        return "040101";
      case "MobiFone":
        return "040103";
      case "Vinaphone":
        return "040102";
      case "GMobile":
        return "040104";
      case "Vietnamobile":
        return "040105";
      default:
        return "040101";
    }
  }

  public static String getGameCardCodeServiceIdByProductName(String productName) throws Exception {
    switch (productName) {
      case "Garena":
        return "040109";
      case "Gate":
        return "040107";
      case "Vcoin":
        return "040108";
      case "Zing":
        return "040106";
      case "Oncach":
        return "040111";
      case "Vinplay":
        return "040110";
      case "OnCash":
        return "040111";
      case "Bit":
        return "040112";
      case "Appota":
        return "040115";
      case "Soha":
        return "040119";
      case "Funcard":
        return "040120";
      case "Scoin":
        return "040121";
      case "Anpay":
        return "040126";
      default:
        return "040109";
    }
  }

  public static String getEPOCardCodeServiceIdByProductName(String productName) throws Exception {

    switch (productName) {
      case "VIETTEL":
        return "070101";
      case "VINAPHONE":
        return "070102";
      case "MOBIFONE":
        return "070103";
      case "GMOBILE":
        return "070104";
      case "VNMOBILE":
        return "070105";
      case "ZING":
        return "070106";
      case "GATE":
        return "070107";
      case "VCOIN":
        return "070108";
      case "GARENA":
        return "070109";
      case "VINPLAY":
        return "070110";
      case "ONCASH":
        return "070111";


      case "DATA_VINAPHONE":
        return "070112";
      default:
        return "070101";
    }

  }

  public static String getDataCardCodeServiceIdByProductName(String productName) throws Exception {
    switch (productName) {
      case "3GViettel":
        return "040118";
      case "3GVina":
        return "040116";
      case "3GMobi":
        return "040117";
      default:
        return "040118";
    }
  }

  public static String isValidPhoneNoBase(String phone) throws Exception {

    if (!regexOnlyNumbers.matcher(phone).find()) {
      return "matches numbers only";
    }

    if (!(phone.length() == 10 || phone.length() == 11)) {
      return "matches 10-digit or 11-digit numbers only";
    }

    if (!detectNumber(phone)) {
      return "Phone doesn't register in VietNam";
    }
    return StringUtils.EMPTY;
  }

  /*
   * Check if a string is started with another string
   *
   * @param (string) ($needle) The string being searched for.
   * @param (string) ($haystack) The string being searched
   * @return (boolean) true if $haystack is started with $needle
   */

  public static boolean startWith(String phone, String startWith) {
    int length = startWith.length();
    return phone.substring(0, length).equals(startWith);
  }

  /*
   * Detect carrier name by phone number
   *
   * @param (string) ($number) The input phone number
   * @return (mixed) Name of the carrier, false if not found
   */
  private static boolean detectNumber(String number) throws Exception {

    // $number is not a phone number
    /*if (!regexMobilePhoneVn.matcher(number).find()) {
      return false;
    }*/

    //TODO Check dau so vietnam

    // Store all start number in an array to search
    if (cardHolderMap == null || cardHolderMap.isEmpty()) {
      getCardHolderByPhoneNumber(number);
    }
    if (number.length() == 10) {

      for (String entry : cardHolderMap.keySet()) {
        if (number.startsWith(entry)) {
          return true;
        }
      }
    } else if (number.length() == 11) {

      for (String entry : cardHolderMap.keySet()) {
        if (number.startsWith(entry)) {
          return true;
        }
      }
    }

    // if not found, return false
    return false;
  }

  public static String hiddenPhone(String phone) {

    StringBuilder sb = new StringBuilder();
    char[] chars = phone.toCharArray();

    int length = phone.length();
    int tenPhoneNumber = 10;
    int eleventPhoneNumber = 11;

    if (length == tenPhoneNumber) {
      for (int i = 0; i < tenPhoneNumber; i++) {
        if (i <= 1 || i >= 8) {
          sb.append(chars[i]);
        } else {
          sb.append(STAR);
        }
      }
    } else if (length == eleventPhoneNumber) {
      for (int i = 0; i < eleventPhoneNumber; i++) {
        if (i <= 2 || i >= 9) {
          sb.append(chars[i]);
        } else {
          sb.append(STAR);
        }
      }
    }
    return sb.toString().replaceFirst("0", "+84");
  }
}
