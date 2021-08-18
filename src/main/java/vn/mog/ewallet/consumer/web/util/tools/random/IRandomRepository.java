package vn.mog.ewallet.consumer.web.util.tools.random;

import java.security.SecureRandom;

public interface IRandomRepository {

  public void clear();

  public SecureRandom getSecureRandom();
}
