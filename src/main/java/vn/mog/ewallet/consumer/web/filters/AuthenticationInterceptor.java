package vn.mog.ewallet.consumer.web.filters;

import static vn.mog.ewallet.consumer.web.exception.MessageNotify.SESSION_MESSAGE_NOTIFY;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.PARAM_ACCESS_TOKEN;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.SESSION_ACCESS_TOKEN;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.SESSION_ACCOUNT_LOGIN;

import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import vn.mog.ewallet.consumer.web.contract.UserLogin;
import vn.mog.ewallet.consumer.web.exception.FrontEndException;
import vn.mog.ewallet.consumer.web.exception.MessageNotify;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.IEpinPurchaseOrderEndpoint;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.IWalletEndpoint;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info.GetSpecialCustomerResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.GetCustomerBalanceResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.GetCustomerGetBalanceRequest;
import vn.mog.ewallet.consumer.web.util.tools.SessionUtil;
import vn.mog.ewallet.consumer.web.utils.WebUtil;

/** Created by binhminh on 22/08/2017. */
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

  private static final Logger LOGGER = LogManager.getLogger(AuthenticationInterceptor.class);

  @Autowired IWalletEndpoint walletEndpoint;
  @Autowired protected IEpinPurchaseOrderEndpoint epinEndpoint;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws IOException, FrontEndException, ServletException {

    String requestURI = request.getRequestURI();

    //    //TODO, Cần xử lý business chỗ này, /error với lõi authenzied
    //    LOGGER.debug("--preHandle--RequestURI--" + request.getRequestURI());

    //    boolean ignoreURL = "/".equals(requestURI) ||
    //                        "/login".equals(requestURI) ||
    //                        "/logout".equals(requestURI) ||
    //                        AbstractController.PATH_ERROR.equals(requestURI);

    //    int statusResponse = response.getStatus();

    //    if (AbstractController.PATH_ERROR.equals(requestURI)) {
    //      if (HttpServletResponse.SC_UNAUTHORIZED == statusResponse ||
    // HttpServletResponse.SC_FORBIDDEN == statusResponse) {
    //        request.setAttribute("errorCode", HttpServletResponse.SC_UNAUTHORIZED);
    //      } else {
    //        request.setAttribute("errorCode", 0);
    //      }
    //      return true;
    //    } else if (ignoreURL) {
    //      return true;
    //    }

    // your logic
    Authentication au = SecurityContextHolder.getContext().getAuthentication();
    boolean isAuthen =
        au != null && au.isAuthenticated() && au instanceof UsernamePasswordAuthenticationToken;

    try {
      if (isAuthen) {
        initAttributeToRequest(request);
        //        return true;
      }
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
    }

    //    new SecurityContextLogoutHandler().logout(request, response, au);
    //    response.sendRedirect("/login");
    //    return false;

    //    check kyc and accept some uri available when user hasnt kyc
    if (SessionUtil.getAttribute("loginSuccess") != null
        && (boolean) SessionUtil.getAttribute("loginSuccess") == true
        && SessionUtil.getAttribute("kyc") != null
        && (boolean) SessionUtil.getAttribute("kyc") == false
        && !requestURI.contains("trans-log")
        && !requestURI.contains("transaction-history")
        && !requestURI.contains("login")
        && !requestURI.contains("logout")
        && !requestURI.contains("password")
        && !requestURI.contains("resend-otp")
        && !requestURI.contains("location")
        && !requestURI.contains("kyc")
        && !requestURI.contains("/account/info")
        && !requestURI.contains("/profile/image-profile/update")
        && !requestURI.contains("/dashboard/index")) {
      response.sendRedirect("/access_denied/kyc");
      return false;
    }
    return true;
  }

  @Override
  public void postHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      ModelAndView modelAndView)
      throws IOException {
    MessageNotify message = (MessageNotify) SessionUtil.getAttribute(SESSION_MESSAGE_NOTIFY);
    String method = request.getMethod();

    if ("GET".equalsIgnoreCase(method) && message != null) {
      modelAndView.addObject(MessageNotify.CODE_ERR, message.getCode());
      modelAndView.addObject(MessageNotify.MES_ERR, message.getMessage());
      SessionUtil.removeAttribute(SESSION_MESSAGE_NOTIFY);
    }
  }

  protected void initAttributeToRequest(HttpServletRequest request) {
    long balance = 0;
    Set<String> role = WebUtil.getRolesOfUserLogin();
    /* boolean roleNotExistBalance = role.contains(JwtRole.ADMIN_OPERATION);
    if (!roleNotExistBalance) {*/

    // }

    UserLogin userLogin = (UserLogin) SessionUtil.getAttribute(SESSION_ACCOUNT_LOGIN);
    if (userLogin == null) {
      userLogin = new UserLogin();
    }

    try {
      GetCustomerBalanceResponse baseResponseType =
          walletEndpoint.getBalanceBySelfCustomer(new GetCustomerGetBalanceRequest());

      if (baseResponseType != null && baseResponseType.getStatus().getCode() == 0) {
        userLogin.setBalance(baseResponseType.getBalance());
        userLogin.setCustomerCif(baseResponseType.getCustomerCif());
      }
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
    }

    SessionUtil.setAttribute(SESSION_ACCOUNT_LOGIN, userLogin);

    String accessToken = (String) SessionUtil.getAttribute(SESSION_ACCESS_TOKEN);
    request.setAttribute(PARAM_ACCESS_TOKEN, accessToken);
  }
}
