package vn.mog.framework.security.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import vn.mog.framework.security.api.CallerInformation;
import vn.mog.framework.security.api.ICallerUtils;
import vn.mog.framework.security.api.ICustomerAware;
import vn.mog.framework.security.api.MobiliserWebAuthenticationDetails;

@Service
public class CallerUtilsImpl implements ICallerUtils {

  @Override
  public long getCallerId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null) {
      throw new IllegalArgumentException(
          "No authentication object found in security context. Check configuration?");
    }

    return getActorId(authentication);
  }

  @Override
  public CallerInformation getCallerInformation() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null) {
      throw new IllegalArgumentException(
          "No authentication object found in security context. Check configuration?");
    }

    Long actorId = Long.valueOf(getActorId(authentication));
    String actorCif = getActorCif(authentication);
    String actorIdentifier = getActorIdentifier(authentication);
    int actorType = getActorType(authentication);

    List<String> actorPrivs = new ArrayList<>();

    for (GrantedAuthority grantedAuth : authentication.getAuthorities()) {
      actorPrivs.add(grantedAuth.getAuthority());
    }

    Object authDetails = authentication.getDetails();
    String userAgent;
    String ip;
    if (authDetails instanceof MobiliserWebAuthenticationDetails) {
      ip = ((MobiliserWebAuthenticationDetails) authDetails).getRemoteAddress();
      userAgent = ((MobiliserWebAuthenticationDetails) authDetails).getUserAgent();
    } else {
      ip = null;
      userAgent = null;
    }

    return new CallerInformation(actorId, actorCif, actorType, actorIdentifier, actorPrivs, ip,
        userAgent);
  }

  @Override
  public boolean hasPrivilege(String privilege) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null) {
      throw new IllegalArgumentException(
          "No authentication object found in security context. Check configuration?");
    }

    for (GrantedAuthority grantedAuth : authentication.getAuthorities()) {
      if (StringUtils.equals(privilege, grantedAuth.getAuthority())) {
        return true;
      }
    }

    return false;
  }

  private long getActorId(Authentication authentication) {
    Object authDetails = authentication.getDetails();

    if (authDetails instanceof ICustomerAware) {
      return ((ICustomerAware) authDetails).getCustomerId();
    }

    Object principal = authentication.getPrincipal();

    if (principal instanceof ICustomerAware) {
      return ((ICustomerAware) principal).getCustomerId();
    }

    return 0L;
  }

  private String getActorCif(Authentication authentication) {
    Object authDetails = authentication.getDetails();

    if (authDetails instanceof ICustomerAware) {
      return ((ICustomerAware) authDetails).getCustomerCif();
    }

    Object principal = authentication.getPrincipal();

    if (principal instanceof ICustomerAware) {
      return ((ICustomerAware) principal).getCustomerCif();
    }
    return null;
  }

  private String getActorIdentifier(Authentication authentication) {
    Object authDetails = authentication.getDetails();

    if (authDetails instanceof ICustomerAware) {
      return ((ICustomerAware) authDetails).getUsername();
    }

    Object principal = authentication.getPrincipal();

    if (principal instanceof ICustomerAware) {
      return ((ICustomerAware) principal).getUsername();
    }
    return null;
  }

  private int getActorType(Authentication authentication) {
    Object authDetails = authentication.getDetails();

    if (authDetails instanceof ICustomerAware) {
      return ((ICustomerAware) authDetails).getCustomerTypeId();
    }

    Object principal = authentication.getPrincipal();

    if (principal instanceof ICustomerAware) {
      return ((ICustomerAware) principal).getCustomerTypeId();
    }
    return 0;
  }
}
