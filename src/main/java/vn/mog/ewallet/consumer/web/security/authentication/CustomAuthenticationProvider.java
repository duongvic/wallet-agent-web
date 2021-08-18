package vn.mog.ewallet.consumer.web.security.authentication;

import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import vn.mog.ewallet.consumer.web.exception.ErrorMessages;
import vn.mog.ewallet.consumer.web.exception.FrontEndException;
import vn.mog.ewallet.consumer.web.exception.MessageNotify;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.ISecurityService;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.IWalletEndpoint;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info.GetConsumerAccountResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info.bean.CustomerType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.JwtUserRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.JwtUserResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.ProfileCheckRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.ProfileCheckResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans.JwtUser;
import vn.mog.ewallet.consumer.web.util.tools.SessionUtil;

import static vn.mog.ewallet.consumer.web.common.KycCodeConstants.APPORVED;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

  private static final Logger LOG = LogManager.getLogger(CustomAuthenticationProvider.class);

  @Autowired ISecurityService securityService;

  @Autowired IWalletEndpoint walletEndpoint;

  @Value("${instance.name}")
  private String INSTANCE_NAME;

  private static final String AGENT_INSTANCE = "AGENT_INSTANCE";
  private static final String MERCHANT_INSTANCE = "MERCHANT_INSTANCE";

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    String password = authentication.getCredentials().toString();

    try {
      JwtUser user = securityService.signIn(username, password);
      GetConsumerAccountResponse res = walletEndpoint.accountInfoGet();
      // check kyc
      if (res.getCustomer().getKycStatus() != APPORVED) {
        SessionUtil.setAttribute("kyc", false);
      }

      LOG.info(
          "authenticated user "
              + ((user == null) ? "null user" : user.getUsername())
              + ", setting security context");
      if (user == null) {
        throw new BadCredentialsException(
            String.valueOf(ErrorMessages.AUTHENTICATION_INVALID.code));
      }
      //      if (!CustomerType.CUSTOMER.equals(user.getCustomerTypeId()) &&
      // !CustomerType.MERCHANT.equals(user.getCustomerTypeId()) &&
      // !CustomerType.AGENT.equals(user.getCustomerTypeId())) {
      //        LOG.error("User exist, but don't betweent CUSTOMER, AGENT, MERCHANT");
      //        throw new
      // BadCredentialsException(String.valueOf(ErrorMessages.AUTHENTICATION_BLOCKED_USER.code));
      //      }
      if (StringUtils.isBlank(INSTANCE_NAME)) {
        LOG.error("Can not get INSTANCE_NAME");
        throw new BadCredentialsException(
            String.valueOf(ErrorMessages.AUTHENTICATION_BLOCKED_USER.code));
      }
      if (AGENT_INSTANCE.equals(INSTANCE_NAME)
          && !CustomerType.AGENT.equals(user.getCustomerTypeId())) {
        LOG.error("User exist, but is not AGENT");
        throw new BadCredentialsException(
            String.valueOf(ErrorMessages.AUTHENTICATION_BLOCKED_USER.code));
      } else if (MERCHANT_INSTANCE.equals(INSTANCE_NAME)
          && !CustomerType.MERCHANT.equals(user.getCustomerTypeId())) {
        LOG.error("User exist, but is not MERCHANT");
        throw new BadCredentialsException(
            String.valueOf(ErrorMessages.AUTHENTICATION_BLOCKED_USER.code));
      }

      SessionUtil.setAttribute("password", password);
      SessionUtil.setAttribute("phone", username);
      if (isDefaultPassword() == true) {
        throw new BadCredentialsException("USING_DEFAULT_PASSWORD");
      } else {
        Authentication auth =
            new UsernamePasswordAuthenticationToken(
                user, user.getUsername(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        return auth;
      }
    } catch (FrontEndException e) {
      LOG.error("authenticate", e);
      throw new BadCredentialsException(e.getErrorMessage());
    } catch (Exception e) {
      LOG.error("authenticate", e);
      throw new BadCredentialsException(e.getMessage()); // check login pass fail
    }
    //    return new AnonymousAuthenticationToken(null, "UNKNOWN", null);
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }

  public boolean isDefaultPassword() {
    String phone = (String) SessionUtil.getAttribute("phone");
    ProfileCheckRequest profileCheckRequest = new ProfileCheckRequest();
    profileCheckRequest.setPhoneNumber(phone);
    boolean check = isUsingDefaultPassword(profileCheckRequest);
    SessionUtil.setAttribute("USING_DEFAULT_PASSWORD", check);
    return check;
  }

  public boolean isUsingDefaultPassword(ProfileCheckRequest profileCheckRequest) {
    try {
      ProfileCheckResponse profileCheckResponse = walletEndpoint.profileCheck(profileCheckRequest);
      if (profileCheckResponse.getIsTempPwdUsing() == true) {
        return true;
      }
      if (profileCheckResponse.getStatus().getCode() != MessageNotify.SUCCESS_CODE) {
        LOG.error("Call api check profile failed: " + profileCheckResponse.getStatus().getValue());
      }
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
    return false;
  }
}
