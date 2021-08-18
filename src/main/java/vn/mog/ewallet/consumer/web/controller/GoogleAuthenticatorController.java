package vn.mog.ewallet.consumer.web.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//@Controller
public class GoogleAuthenticatorController extends AbstractController {

  private static final Logger LOG = LogManager.getLogger(GoogleAuthenticatorController.class);

  /*@RequestMapping(value = "/otp", method = RequestMethod.GET)
  public String otpAuthenticator(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (!(auth != null && auth.isAuthenticated()&& auth instanceof UsernamePasswordAuthenticationToken)) {
      return SharedConstants.EWALLET_WEB_DOMAIN_URL_LOGIN;
    }

    UserLogin userLogin = (UserLogin) SessionUtil.getAttribute(SESSION_ACCOUNT_LOGIN);
    if (userLogin != null) {
      Integer customerTypeId = userLogin.getCustomerTypeId();
      if (CustomerType.ID_ADMIN.equals(customerTypeId) || CustomerType.ID_STAFF.equals(customerTypeId)) {

      } else {
        request.getSession().setAttribute("otp_logined", "1");
        return "redirect:/";
      }
    }

    return "/auth/otp";
  }*/

  /*@RequestMapping(value = "/otp", method = RequestMethod.POST)
  public String postAuthenticator(HttpServletRequest request, HttpServletResponse response, ModelMap map) {

    try {
      Customer customer = getUserCustomer();
      if (customer != null) {
        CustomerType customerType = customer.getCustomerType();
        Integer customerTypeId = customerType.getId();
        if (CustomerType.ID_ADMIN.equals(customerTypeId) || CustomerType.ID_STAFF.equals(customerTypeId)) {

          if (request.getParameter("code") != null) {
            GoogleAuthenticatorConfigBuilder gacb = new GoogleAuthenticatorConfigBuilder()
                .setTimeStepSizeInMillis(TimeUnit.SECONDS.toMillis(30))
                .setWindowSize(5);
            GoogleAuthenticator ga = new GoogleAuthenticator(gacb.build());

            String code = request.getParameter("code");
            String secret = customer.getSecret();
            Integer icode = Integer.parseInt(code);

            boolean isCodeValid = ga.authorize(secret, icode);
            if (isCodeValid) {
              request.getSession().setAttribute("otp_logined", "1");
              return "redirect:" + redirectRole();
            } else {
              map.put("codeErr", 1);
              map.put("mesErr", "Nhập sai OTP!");
            }
          }
        } else {
          request.getSession().setAttribute("otp_logined", "1");
          return "redirect:" + redirectRole();
        }
      }
    } catch (Exception e) {
      map.put("codeErr", 1);
      map.put("mesErr", e.getMessage());
      log.error("postAuthenticator", e);
    }

    return "/auth/otp";
  }*/


}
