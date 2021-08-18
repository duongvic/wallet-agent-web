package vn.mog.ewallet.consumer.web.security.authentication;

import static vn.mog.ewallet.consumer.web.controller.dashboard.DashboardController.DASHBOARD_LIST;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import vn.mog.ewallet.consumer.web.util.tools.SessionUtil;

@Service
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException {
    SessionUtil.setAttribute("loginSuccess", true);
    response.sendRedirect(DASHBOARD_LIST);
  }
}
