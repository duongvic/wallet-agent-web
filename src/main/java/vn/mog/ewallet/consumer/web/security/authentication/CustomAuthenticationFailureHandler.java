package vn.mog.ewallet.consumer.web.security.authentication;

import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

@Service
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

  private final static Logger LOGGER = LogManager.getLogger(CustomAuthenticationFailureHandler.class);

  @Override
  public void onAuthenticationFailure(HttpServletRequest request,
      HttpServletResponse response, AuthenticationException exception)
      throws IOException, ServletException {

    LOGGER.info(String.format("### AuthenticationFailureHandler: URL [%s] ", request.getRequestURI()));
    if(exception.getMessage().equals("USING_DEFAULT_PASSWORD")){
      response.sendRedirect("/change-default-password");
    }else{
      response.sendRedirect("/login?error=" + URLEncoder.encode(exception.getMessage(), "UTF-8"));
    }

  }

}
