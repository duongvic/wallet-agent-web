package vn.mog.ewallet.consumer.web.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans.LogFile;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans.JwtUser;


public class WebUtil {

  public static final String SSO_PARAM = "sso";
  private static final Logger LOG = LogManager.getLogger(WebUtil.class);
  public static String MERCHANT_DEFAULT = "/template/transaction_logs_template_default.csv";

  public static Set<String> getRolesOfUserLogin() {
    try {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      if (auth != null && auth.isAuthenticated() && auth instanceof UsernamePasswordAuthenticationToken) {
        JwtUser account = (JwtUser) auth.getPrincipal();
        return account.getRoles();
      }
    } catch (ClassCastException e) {
      LOG.error(e.getMessage(), e);
    }
    return Collections.emptySet();
  }



  public static void exportFile(HttpServletResponse response, LogFile logFile) throws IOException {
    if (logFile != null) {
      byte[] bytes = logFile.getContent();

      response.reset();
      response.resetBuffer();
      response.setContentType(logFile.getContentType());
      response.setContentLength(bytes.length);
      response.setCharacterEncoding("UTF-8");
      response.addHeader("Content-disposition", "attachment;filename=" + logFile.getName());

      ServletOutputStream ouputStream = response.getOutputStream();
      ouputStream.write(bytes, 0, bytes.length);
      ouputStream.flush();
      ouputStream.close();
    } else {

      InputStream file = new ClassPathResource(MERCHANT_DEFAULT).getInputStream();
      byte[] bytes = IOUtils.toByteArray(file);

      response.reset();
      response.resetBuffer();
      response.setContentType("text/csv");
      response.setContentLength(bytes.length);
      response.setCharacterEncoding("UTF-8");
      response.addHeader("Content-disposition", "attachment;filename=Log_giao_dich.csv");

      ServletOutputStream ouputStream = response.getOutputStream();
      ouputStream.write(bytes, 0, bytes.length);
      ouputStream.flush();
      ouputStream.close();
    }
  }

  public static Map<String, String> defaultMapHeader() {
    Map<String, String> mapHeader =  new HashMap<>();
    mapHeader.put("Authorization",null);
    mapHeader.put("Content-Type", MediaType.APPLICATION_JSON_VALUE);
    mapHeader.put("X-language","en");
    return mapHeader;
  }

  public static Map<String, String> defaultUriVariables() {
    Map<String, String> uriVariables = new HashMap<>();
    return uriVariables;
  }

  public static Map<String, String> defaultNullUriVariables() {
    Map<String, String> uriVariables = null;
    return uriVariables;
  }
}
