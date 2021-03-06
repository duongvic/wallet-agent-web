package vn.mog.ewallet.consumer.web.filters;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Created by binhminh on 29/09/2017.
 */
public class XSSRequestWrapper extends HttpServletRequestWrapper {

  /**
   * Constructs a request object wrapping the given request.
   *
   * @param request The request to wrap
   * @throws IllegalArgumentException if the request is null
   */
  public XSSRequestWrapper(HttpServletRequest request) {
    super(request);
  }

  @Override

  public String[] getParameterValues(String parameter) {
    String[] values = super.getParameterValues(parameter);
    if (values == null) {
      return null;
    }
    int count = values.length;
    String[] encodedValues = new String[count];
    for (int i = 0; i < count; i++) {
      encodedValues[i] = stripXSS(values[i]);
    }
    return encodedValues;
  }

  @Override

  public String getParameter(String parameter) {
    String value = super.getParameter(parameter);
    return stripXSS(value);
  }

  @Override

  public String getHeader(String name) {
    String value = super.getHeader(name);
    return stripXSS(value);
  }

  private String stripXSS(String value) {
    if (value != null) {
      // NOTE: It's highly recommended to use the ESAPI library and uncomment the following line to

      // avoid encoded attacks.
      // value = ESAPI.encoder().canonicalize(value);

      // Avoid null characters
      value = value.replaceAll(EMPTY, EMPTY);

      // Avoid anything between script tags
      Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
      value = scriptPattern.matcher(value).replaceAll(EMPTY);

      // Avoid anything in a src='...' type of expression
      scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
      value = scriptPattern.matcher(value).replaceAll(EMPTY);

      scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
      value = scriptPattern.matcher(value).replaceAll(EMPTY);

      // Remove any lonesome </script> tag
      scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
      value = scriptPattern.matcher(value).replaceAll(EMPTY);

      // Remove any lonesome <script ...> tag
      scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
      value = scriptPattern.matcher(value).replaceAll(EMPTY);

      // Avoid eval(...) expressions
      scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
      value = scriptPattern.matcher(value).replaceAll(EMPTY);

      // Avoid expression(...) expressions
      scriptPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

      value = scriptPattern.matcher(value).replaceAll(EMPTY);

      // Avoid javascript:... expressions
      scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
      value = scriptPattern.matcher(value).replaceAll(EMPTY);

      // Avoid vbscript:... expressions
      value = scriptPattern.matcher(value).replaceAll(EMPTY);

      // Avoid onload= expressions
      scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
      value = scriptPattern.matcher(value).replaceAll(EMPTY);
    }
    return value;
  }

}
