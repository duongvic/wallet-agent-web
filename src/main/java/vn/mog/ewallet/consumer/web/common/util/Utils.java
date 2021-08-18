package vn.mog.ewallet.consumer.web.common.util;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Utils {

  private static final Logger logger = LogManager.getLogger(Utils.class);

  private final static String TIME_ZONE = "GMT";
  public static DateFormat sDateFormatGMT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
  private static Gson smGson = new Gson();
  private static ObjectMapper smObjectMapper = new ObjectMapper();

  public static <T> String convertToString(T t) {
    return smGson.toJson(t);
  }

  public static <T> T convertFromString(String input, Class<T> clazz) {
    try {
      return smObjectMapper.readValue(input, clazz);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
    return null;
  }

  public static String getCurrentGMTTimeAsString() {
    sDateFormatGMT.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
    return sDateFormatGMT.format(new Date());
  }

  public static Date getGMTTimeFromString(String sDate, String format) {
    DateFormat df = sDateFormatGMT;
    if (StringUtils.isNotEmpty(format))
      df = new SimpleDateFormat(format);
    df.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
    try {
      return df.parse(sDate);
    } catch (ParseException e) {
      logger.error(e.getMessage(), e);
    }
    return null;
  }


  public static int getQuarterOfDate(Date myDate) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(myDate);
    int month = cal.get(Calendar.MONTH); /* 0 through 11 */
    int quarter = (month / 3) + 1;
    return quarter;
  }

  public static Date getFirstDayOfQuarter(int quarter) {
    Calendar cal = Calendar.getInstance();
    int year = cal.get(Calendar.YEAR);
    return getFirstDayOfQuarter(quarter, year);
  }

  public static Date getFirstDayOfQuarter(int quarter, int year) {
    int month = 1;
    switch (quarter) {
      case 1:
        month = 1;
        break;
      case 2:
        month = 4;
        break;
      case 3:
        month = 7;
        break;
      case 4:
        month = 10;
        break;
      default:
        break;
    }
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, year);
    calendar.set(Calendar.MONTH, month - 1);
    calendar.set(Calendar.DAY_OF_MONTH, 1);
    calendar.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the
    calendar.clear(Calendar.MINUTE);
    calendar.clear(Calendar.SECOND);
    calendar.clear(Calendar.MILLISECOND);
    // get start of the month
    return calendar.getTime();
  }

  public static Date getEndDayOfQuarter(int quarter) {
    Calendar cal = Calendar.getInstance();
    int year = cal.get(Calendar.YEAR);
    return getEndDayOfQuarter(quarter, year);
  }

  public static Date getEndDayOfQuarter(int quarter, int year) {
    int month = 3;
    switch (quarter) {
      case 1:
        month = 3;
        break;
      case 2:
        month = 6;
        break;
      case 3:
        month = 9;
        break;
      case 4:
        month = 12;
        break;
      default:
        break;
    }
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, year);
    calendar.set(Calendar.MONTH, month - 1);
    calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
    calendar.set(Calendar.HOUR_OF_DAY, 23);
    calendar.set(Calendar.MINUTE, 59);
    calendar.set(Calendar.SECOND, 59);
    calendar.set(Calendar.MILLISECOND, 999);
    // get end of the month
    return calendar.getTime();
  }

  public static int getDaysOfQuarter(int quarter) {
    Date firstDate = getFirstDayOfQuarter(quarter);
    Date endDate = getEndDayOfQuarter(quarter);
    return daysBetween(firstDate, endDate);
  }

  public static boolean isSameDay(Date date1, Date date2) {
    Calendar cal1 = Calendar.getInstance();
    Calendar cal2 = Calendar.getInstance();
    cal1.setTime(date1);
    cal2.setTime(date2);
    boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
        && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    return sameDay;
  }

  public static boolean isSameMonth(Date date1, Date date2) {
    Calendar cal1 = Calendar.getInstance();
    Calendar cal2 = Calendar.getInstance();
    cal1.setTime(date1);
    cal2.setTime(date2);
    boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
        && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
    return sameDay;
  }

  public static Date getStartOfHour(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    // start hour of day !
    calendar.clear(Calendar.MINUTE);
    calendar.clear(Calendar.SECOND);
    calendar.clear(Calendar.MILLISECOND);
    return calendar.getTime();
  }

  public static Date getEndOfHour(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    // end hour of day !
    calendar.set(Calendar.MINUTE, 59);
    calendar.set(Calendar.SECOND, 59);
    calendar.set(Calendar.MILLISECOND, 999);
    return calendar.getTime();
  }

  public static Date getStartOfDay(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DATE);
    calendar.set(year, month, day, 0, 0, 0);
    return calendar.getTime();
  }

  public static Date getEndOfDay(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DATE);
    calendar.set(year, month, day, 23, 59, 59);
    return calendar.getTime();
  }

  public static Date getStartOfWeek(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the
    // hour of day !
    calendar.clear(Calendar.MINUTE);
    calendar.clear(Calendar.SECOND);
    calendar.clear(Calendar.MILLISECOND);

    // get start of this week in milliseconds
    calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
    return calendar.getTime();
  }

  public static Date getEndOfWeek(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the
    // hour of day !
    calendar.clear(Calendar.MINUTE);
    calendar.clear(Calendar.SECOND);
    calendar.clear(Calendar.MILLISECOND);

    // get start of this week in milliseconds
    calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
    Calendar last = (Calendar) calendar.clone();
    last.add(Calendar.DAY_OF_YEAR, 6);
    last.set(Calendar.HOUR_OF_DAY, 23);
    last.set(Calendar.MINUTE, 59);
    last.set(Calendar.SECOND, 59);
    last.set(Calendar.MILLISECOND, 999);
    return last.getTime();
  }

  public static Date getStartOfMonth(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);

    calendar.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the
    calendar.clear(Calendar.MINUTE);
    calendar.clear(Calendar.SECOND);
    calendar.clear(Calendar.MILLISECOND);

    // get start of the month
    calendar.set(Calendar.DAY_OF_MONTH, 1);
    return calendar.getTime();
  }

  public static Date getEndOfMonth(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
    calendar.set(Calendar.HOUR_OF_DAY, 23);
    calendar.set(Calendar.MINUTE, 59);
    calendar.set(Calendar.SECOND, 59);
    calendar.set(Calendar.MILLISECOND, 999);
    // get start of the month
    return calendar.getTime();
  }

  public static int daysBetween(Date fromDate, Date toDate) {
    return (int) ((toDate.getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24));
  }

  public static int hourOfDay(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar.get(Calendar.HOUR_OF_DAY);
  }

  public static int monthOfDay(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar.get(Calendar.MONTH) + 1;
  }

  public static int yearOfDay(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar.get(Calendar.YEAR);
  }

  public static Date dateAdd(Date date, int numOfDay) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.DATE, numOfDay);
    return calendar.getTime();
  }

  private static MessageDigest msgDigest = null;
  static {
    try {
      msgDigest = MessageDigest.getInstance("MD5");
    } catch (Exception ex) {
    }
  }

  public static String encryptMD5(String message) {
    String digest = null;
    try {
      byte[] hash = msgDigest.digest(message.getBytes("UTF-8"));
      StringBuilder sb = new StringBuilder(2 * hash.length);
      for (byte b : hash) {
        sb.append(String.format("%02x", b & 0xff));
      }
      digest = sb.toString();
    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage(), e);
    }
    return digest;
  }

  // private static String
  // CHARACTERS="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
  private static String CHARACTERS = "0123456789abcdefghijklmnopqrstuvwxyz";
  private static String CHARACTERS_STRING = "abcdefghijklmnopqrstuvwxyz";

  public static String generateAccountSalt() {
    return RandomStringUtils.random(32, CHARACTERS);
  }

  public static String generateAccountAccessKey() {
    return RandomStringUtils.random(20, CHARACTERS);
  }

  public static String generateAccountSecret() {
    return RandomStringUtils.random(32, CHARACTERS);
  }

  public static String generateAccountCode() {
    return RandomStringUtils.randomNumeric(6);
  }

  public static String getAccountHashedPassword(String password, String salt) {
    String hashedPassword = Utils.encryptMD5(password + salt);
    return hashedPassword;
  }

  public static String getRandomString(int length) {
    if (length == 0) {
      return RandomStringUtils.random(32, CHARACTERS_STRING);
    }
    return RandomStringUtils.random(length, CHARACTERS_STRING);
  }

  public static String generateRandomString(int length) {
    if (length == 0) {
      return RandomStringUtils.random(32, CHARACTERS);
    }
    return RandomStringUtils.random(length, CHARACTERS);
  }

  public static String[] mobileOperators =
      {"VNP", "VMS", "VTM", "SFE", "VNM", "BEL", "GATE", "VCOIN", "ZING", "UNKNOWN"};
  public static String[] mobileOperatorNames = {"Vinaphone", "Mobifone", "Viettel", "SFone",
      "Vietnamobile", "Beeline", "Gate - FPT", "VCoin - VTC", "Zing - VNG ", "KhÃ¡c"};
  public String[] VNP = {"8491", "8494", "84123", "84124", "84125", "84127", "84129"};
  public String[] VMS = {"8490", "8493", "84121", "84122", "84126", "84128", "84120"};
  public String[] VTM = {"8498", "8497", "84165", "84166", "84167", "84168", "84169", "84164",
      "84163", "84162", "8496"};
  public String[] SFE = {"8495"};
  public String[] VNM = {"8492", "84188", "84186"};
  public String[] BEL = {"84199", "84996"};
  public String[] GATE = {};
  public String[] VCOIN = {};
  public String[] ZING = {};
  public String[] UNKNOWN = {};

  public static String findMobileOperator(String mobileNumber) {
    mobileNumber = getFormatedMsisdn(mobileNumber);
    String result = null;
    for (int i = 0; i < mobileOperators.length; i++) {
      try {
        Utils util = new Utils();
        String[] short_number = (String[]) Utils.class.getField(mobileOperators[i]).get(util);
        for (int j = 0; j < short_number.length; j++) {
          if (mobileNumber.startsWith(short_number[j])) {
            return mobileOperatorNames[i];
          }
        }
      } catch (Exception e) {
        logger.error(e.getMessage(), e);
      }
    }
    return result;
  }

  public static String findMobileOperatorCode(String mobileNumber) {
    mobileNumber = getFormatedMsisdn(mobileNumber);
    String result = null;
    for (int i = 0; i < mobileOperators.length; i++) {
      try {
        Utils util = new Utils();
        String[] short_number = (String[]) Utils.class.getField(mobileOperators[i]).get(util);
        for (int j = 0; j < short_number.length; j++) {
          if (mobileNumber.startsWith(short_number[j])) {
            return mobileOperators[i];
          }
        }
      } catch (Exception e) {
        logger.info(mobileNumber);
        logger.error(e.getMessage(), e);
      }
    }
    if (result == null)
      System.out.println(mobileNumber);
    return result;
  }

  public static String findMobileOperatorByCode(String code) {
    for (int i = 0; i < mobileOperators.length; i++) {
      if (code.equalsIgnoreCase(mobileOperators[i]))
        return mobileOperatorNames[i];
    }
    return code;
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  public static Map sortByComparator(Map unsortMap, final boolean asc) {

    List list = new LinkedList(unsortMap.entrySet());

    // sort list based on comparator
    Collections.sort(list, new Comparator() {
      public int compare(Object o1, Object o2) {
        if (asc)
          return ((Comparable) ((Map.Entry) (o1)).getValue())
              .compareTo(((Map.Entry) (o2)).getValue());
        else
          return ((Comparable) ((Map.Entry) (o2)).getValue())
              .compareTo(((Map.Entry) (o1)).getValue());
      }
    });

    // put sorted list into map again
    // LinkedHashMap make sure order in which keys were inserted
    Map sortedMap = new LinkedHashMap();
    for (Iterator it = list.iterator(); it.hasNext();) {
      Map.Entry entry = (Map.Entry) it.next();
      sortedMap.put(entry.getKey(), entry.getValue());
    }
    return sortedMap;
  }

  public static Date convertToMongoDBTime(Date date) {
    if (date != null) {
      try {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) + 7);
        return cal.getTime();
      } catch (Exception e) {
        logger.error(e.getMessage(), e);
      }
    }
    return date;
  }

  public static Date mongoDbTimeToDisplayTime(Date date) {
    if (date != null) {
      try {
        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(date); // sets calendar time/date
        cal.add(Calendar.HOUR_OF_DAY, -7); // adds one hour
        return cal.getTime();
      } catch (Exception e) {
        logger.error(e.getMessage(), e);
      }
    }
    return date;
  }

  public static Date convertToVNTime(Date date) {
    if (date != null) {
      try {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) + 7);
        return cal.getTime();
      } catch (Exception e) {
        logger.error(e.getMessage(), e);
      }
    }
    return date;
  }

  public static Date convertToGMTTime(Date date) {
    if (date != null) {
      try {
        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(date); // sets calendar time/date
        cal.add(Calendar.HOUR_OF_DAY, -7); // adds one hour
        return cal.getTime();
      } catch (Exception e) {
        logger.error(e.getMessage(), e);
      }
    }
    return date;
  }

  private final static Pattern IPPattern = Pattern.compile("\\d+\\.\\d+\\.\\d+\\.\\d+");

  public static String getIP(HttpServletRequest req) {
    String ip = req.getRemoteAddr();
    // if (ip != null && (ip.startsWith("127.") || ip.startsWith("192.168."))) {

    String userAgent = req.getHeader("User-Agent") != null ? req.getHeader("User-Agent")
        : req.getHeader("user-agent");
    if (StringUtils.isNotEmpty(userAgent) && (userAgent.toLowerCase().indexOf("opera") != -1)
        && (userAgent.toLowerCase().indexOf("mini") != -1)) {
      ip = req.getHeader("HTTP_X_FORWARDED_FOR") != null ? req.getHeader("HTTP_X_FORWARDED_FOR")
          : req.getHeader("x-forwarded-for") != null ? req.getHeader("x-forwarded-for")
              : req.getHeader("X-Forwarded-For");
    } else {
      ip = StringUtils.isNotEmpty(req.getHeader("vx-forwarded-for"))
          ? req.getHeader("vx-forwarded-for")
          : req.getHeader("x-forwarded-for") != null ? req.getHeader("x-forwarded-for")
              : req.getHeader("X-Forwarded-For");
    }
    if (ip != null) {
      Matcher m = IPPattern.matcher(ip);
      if (m.find()) {
        ip = m.group(0);
      } else
        ip = null;
    } else {
      ip = req.getRemoteAddr();
    }

    // }
    return ip;
  }

  private static final Pattern EMAIL_PATTERN = Pattern.compile(
      "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})",
      Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

  public static boolean validateEmail(String email) {
    Matcher matcher = EMAIL_PATTERN.matcher(email);
    if (matcher.find())
      return true;
    return false;
  }

  public static String numberToText(String number) {
    number = number.replaceAll("\\D+", "");
    String[] dv = {"", "mÆ°Æ¡i", "trÄƒm", "nghÃ¬n", "triá»‡u", "tá»‰"};
    String[] cs =
        {"khÃ´ng", "má»™t", "hai", "ba", "bá»‘n", "nÄƒm", "sÃ¡u", "báº£y", "tÃ¡m", "chÃ­n"};
    String doc;
    int i, j, k, n, len, found, ddv, rd;

    len = number.length();
    number += "ss";
    doc = "";
    found = 0;
    ddv = 0;
    rd = 0;

    i = 0;
    while (i < len) {
      // So chu so o hang dang duyet
      n = (len - i + 2) % 3 + 1;

      // Kiem tra so 0
      found = 0;
      for (j = 0; j < n; j++) {
        if (number.charAt(i + j) != '0') {
          found = 1;
          break;
        }
      }

      // Duyet n chu so
      if (found == 1) {
        rd = 1;
        for (j = 0; j < n; j++) {
          ddv = 1;
          switch (number.charAt(i + j)) {
            case '0':
              if (n - j == 3)
                doc += cs[0] + " ";
              if (n - j == 2) {
                if (number.charAt(i + j + 1) != '0')
                  doc += "láº» ";
                ddv = 0;
              }
              break;
            case '1':
              if (n - j == 3)
                doc += cs[1] + " ";
              if (n - j == 2) {
                doc += "mÆ°á»�i ";
                ddv = 0;
              }
              if (n - j == 1) {
                if (i + j == 0)
                  k = 0;
                else
                  k = i + j - 1;

                if (number.charAt(k) != '1' && number.charAt(k) != '0')
                  doc += "má»‘t ";
                else
                  doc += cs[1] + " ";
              }
              break;
            case '5':
              if (i + j == len - 1)
                doc += "lÄƒm ";
              else
                doc += cs[5] + " ";
              break;
            default:
              doc += cs[(int) number.charAt(i + j) - 48] + " ";
              break;
          }

          // Doc don vi nho
          if (ddv == 1) {
            doc += dv[n - j - 1] + " ";
          }
        }
      }

      // Doc don vi lon
      if (len - i - n > 0) {
        if ((len - i - n) % 9 == 0) {
          if (rd == 1)
            for (k = 0; k < (len - i - n) / 9; k++)
              doc += "tá»‰ ";
          rd = 0;
        } else if (found != 0)
          doc += dv[((len - i - n + 1) % 9) / 3 + 2] + " ";
      }

      i += n;
    }

    if (len == 1)
      if (number.charAt(0) == '0' || number.charAt(0) == '5')
        return cs[(int) number.charAt(0) - 48];

    doc = Character.toUpperCase(doc.charAt(0)) + doc.substring(1);
    return doc;
  }

  public static String formatNumber(String number, int length) {
    if (number.length() > length) {
      return number.substring(0, length - 1);
    } else {
      int remain = length - number.length();
      StringBuffer buffer = new StringBuffer();
      while (remain > 0) {
        buffer.append('0');
        remain--;
      }
      buffer.append(number);
      return buffer.toString();
    }
  }

  public static String formatNumber(double number) {
    // String pattern = "###,###.##";
    String pattern = "###,###";
    DecimalFormat myFormatter = new DecimalFormat(pattern);
    String output = myFormatter.format(number);
    return output;
  }

  public static String formatNumber(float number) {
    double value = (double) number;
    // String pattern = "###,###.##";
    String pattern = "###,###";
    DecimalFormat myFormatter = new DecimalFormat(pattern);
    String output = myFormatter.format(value);
    return output;
  }

  public static String numberToText(float number) {
    return numberToText(formatNumber(number));
  }

  public static String numberToText(double number) {
    return numberToText(formatNumber(number));
  }

  public static long doubleToLong(double d) {
    // mashing it all into one function of methods
    return new Long(String.format("%.0f", d)).longValue();
  }

  public static double longToDouble(long l) {
    // mashing it all into one function of methods
    return (double) l;
  }

  public static void printMap(Map<String, String> map) {
    for (Map.Entry<String, String> entry : map.entrySet()) {
      System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
    }
  }

  public static String getFormatedMsisdn(String msisdn) {
    msisdn = StringUtils.trimToEmpty(msisdn);
    msisdn = StringUtils.trimToEmpty(msisdn.replaceAll("\\D+", ""));
    if (StringUtils.isNotEmpty(msisdn)) {
      if (msisdn.startsWith("84"))
        return msisdn;
      else
        msisdn = "84" + (msisdn.startsWith("0") ? msisdn.substring(1) : msisdn);
    }
    return StringUtils.trimToEmpty(msisdn);
  }

  public static int wordCount(String s) {

    int wordCount = 0;

    boolean word = false;
    int endOfLine = s.length() - 1;

    for (int i = 0; i < s.length(); i++) {
      // if the char is a letter, word = true.
      if (Character.isLetter(s.charAt(i)) && i != endOfLine) {
        word = true;
        // if char isn't a letter and there have been letters before,
        // counter goes up.
      } else if (!Character.isLetter(s.charAt(i)) && word) {
        wordCount++;
        word = false;
        // last word of String; if it doesn't end with a non letter, it
        // wouldn't count without this.
      } else if (Character.isLetter(s.charAt(i)) && i == endOfLine) {
        wordCount++;
      }
    }
    return wordCount;
  }

  public static String encryptMD5(String salt, String raw) {
    return DigestUtils.md5Hex(raw + salt);
  }

  public static String encryptToMD5(String raw) {
    return DigestUtils.md5Hex(raw);
  }

  public static String objectToJson(Object obj) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      String json = objectMapper.writeValueAsString(obj);
      return json;
    } catch (JsonProcessingException e) {
      logger.error(e.getMessage(), e);
    }
    return null;
  }

  public static <T> T jsonToObject(String json, Class<T> classOfT) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      return objectMapper.readValue(json, classOfT);
    } catch (JsonGenerationException e) {
      logger.error(e.getMessage(), e);
    } catch (JsonMappingException e) {
      logger.error(e.getMessage(), e);
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    }
    return null;
  }


  public static String join(List<String> items, String joinString) {

    if (items.size() > 0) {
      StringBuilder result = new StringBuilder();

      for (String item : items) {
        result.append(item).append(joinString);
      }

      result.deleteCharAt(result.length() - 1);

      return result.toString();
    } else {
      return "";
    }
  }

  public static List<String> listFilesForFolder(final File folder) {
    List<String> filePaths = new ArrayList<>();
    for (final File fileEntry : folder.listFiles()) {
      if (fileEntry.isDirectory()) {
        listFilesForFolder(fileEntry);
      } else {
        filePaths.add(fileEntry.getAbsolutePath());
      }
    }
    return filePaths;
  }

  public static byte[] readBytesFromFile(String filePath) {

    FileInputStream fileInputStream = null;
    byte[] bytesArray = null;

    try {

      File file = new File(filePath);
      bytesArray = new byte[(int) file.length()];

      // read file into bytes[]
      fileInputStream = new FileInputStream(file);
      fileInputStream.read(bytesArray);

    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    } finally {
      if (fileInputStream != null) {
        try {
          fileInputStream.close();
        } catch (IOException e) {
          logger.error(e.getMessage(), e);
        }
      }
    }
    return bytesArray;
  }

  public static void writeBytesToFile(byte[] bFile, String fileDest) {

    FileOutputStream fileOuputStream = null;

    try {
      fileOuputStream = new FileOutputStream(fileDest);
      fileOuputStream.write(bFile);

    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    } finally {
      if (fileOuputStream != null) {
        try {
          fileOuputStream.close();
        } catch (IOException e) {
          logger.error(e.getMessage(), e);
        }
      }
    }
  }

  public static void main(String[] args) throws Exception {
    // String pass = RandomStringUtils.random(32, 0, 0, true, true, null,new
    // SecureRandom());
    // pass = RandomStringUtils.random(32, CHARACTERS);
    // System.out.println(pass);
    // String raw_password = "123456";
    // String salt = "10rcqkomec5krvn5xctf8vyuwwzqc0ta";
    // String hashedPwd = encryptMD5(raw_password + salt);
    // System.out.println(hashedPwd);
    // DateFormat dtFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    // Date date = getFirstDayOfQuarter(1);
    // System.out.println(dtFormat.format(date));
    // date = getEndDayOfQuarter(1);
    // System.out.println(dtFormat.format(date));
    /*
     * String message = "10|The chua duoc kich hoat hoac khong ton tai";
     * if(message.indexOf("|")!=-1){ message = message.substring(message.indexOf("|")+1); }
     * System.out.println(VnUtils.removeTone(message)); System.out.println(wordCount(message));
     */

    /*
     * String salt = Utils.generateAccountSalt(); String hashedPassword =
     * DigestUtils.md5Hex("111111" + salt); System.out.println("salt: " + salt);
     * System.out.println("hashedPassword: " + hashedPassword);
     */

    // System.out.println(generateAccountSecret());
    System.out.println(encryptMD5("123456"));
    //
    // List<IKeyValue> attributes = new ArrayList<>();
    // attributes.add(new KeyValueWrapper(new
    // KeyValue(ServiceAttributeType.TELCO_TYPE.name(),"VTM")));
    // attributes.add(new KeyValueWrapper(new
    // KeyValue(ServiceAttributeType.TELCO_SERVICE_TYPE.name(),"PREPAID")));
    // attributes.add(new KeyValueWrapper(new
    // KeyValue(ServiceAttributeType.TELCO_SERVICE_TYPE.name(),"PREPAID")));
    // String sql ="SELECT distinct(st.ID_SERVICE) FROM (SELECT ID_SERVICE FROM
    // CNF_SERVICE_ATTRIBUTE WHERE 1=1) as st";
    // int count=0;
    // for(IKeyValue keyValue: attributes){
    // count++;
    // sql+="\n JOIN (select ID_SERVICE FROM CNF_SERVICE_ATTRIBUTE WHERE ID_SERVICE_ATTRIBUTE_TYPE
    // ='"+keyValue.getKey()+"' AND STR_VALUE='"+keyValue.getValue()+"') as st"+count;
    // sql+=("\n ON st.ID_SERVICE=st"+count+".ID_SERVICE");
    // }
    // System.out.println(sql);
  }
}
