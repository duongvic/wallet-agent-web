package vn.mog.ewallet.consumer.web.security.authentication;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

  private static Logger LOG = LogManager.getLogger(CustomAccessDeniedHandler.class);

  private String access_denied = "/access_denied";

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException exception) throws IOException, ServletException {

    LOG.info(String.format("### AccessDeniedHandler: URL [%s] ", request.getServletPath()));
    request.getRequestDispatcher(access_denied).forward(request, response);
  }
}
