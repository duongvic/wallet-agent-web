package vn.mog.ewallet.consumer.web.util.tools;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

  // The path the cookie will be available to, edit this to use a different
  // cookie path.
  private static final String COOKIE_PATH = "/";
  // Two years in seconds.
  private static final int COOKIE_USER_PERSISTENCE = 63072000;
  // cookie version
  private static final int COOKIE_VERSION = 1;

  public static boolean isVNXX(String str) {
    if (str == null) {
      return false;
    }
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) > 128) {
        return false;
      }
    }
    return true;
  }

  public static String getCookieValue(HttpServletRequest request, String name) {
    try {
      Cookie[] cookies = request.getCookies();
      if (cookies != null) {
        for (Cookie cookie : cookies) {
          if (cookie != null && name.equals(cookie.getName())) {
            return cookie.getValue();
          }
        }
      }
    } catch (Exception exp) {
      exp.printStackTrace();
    }
    return null;
  }

  public static void setCookieValue(HttpServletResponse response, String name, String value,
      int maxAge) {
    try {
      maxAge = maxAge == Integer.MAX_VALUE ? COOKIE_USER_PERSISTENCE : maxAge;
      Cookie cookie = new Cookie(name, value);
      cookie.setVersion(COOKIE_VERSION);
      cookie.setMaxAge(maxAge);
      cookie.setPath(COOKIE_PATH);
      response.addCookie(cookie);
    } catch (Exception exp) {
      exp.printStackTrace();
    }
  }

  public static void setCookieValue(HttpServletResponse response, String name, String value,
      int maxAge, String domain) {
    try {
      maxAge = maxAge == Integer.MAX_VALUE ? COOKIE_USER_PERSISTENCE : maxAge;
      Cookie cookie = new Cookie(name, value);
      cookie.setVersion(COOKIE_VERSION);
      cookie.setMaxAge(maxAge);
      cookie.setDomain(domain);
      cookie.setPath(COOKIE_PATH);
      response.addCookie(cookie);
    } catch (Exception exp) {
      exp.printStackTrace();
    }
  }

  public static void removeCookie(HttpServletResponse response, String name) {
    setCookieValue(response, name, null, 0);
  }

  public static String implode(String[] ary, String delim) {
    String out = "";
    for (int i = 0; i < ary.length; i++) {
      if (i != 0) {
        out += delim;
      }
      out += ary[i];
    }
    return out;
  }
}
