package vn.mog.ewallet.consumer.web.configuration;

import java.util.EnumSet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionTrackingMode;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;


@Configuration
public class WebApplicationConfiguration implements WebApplicationInitializer {

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    servletContext.setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE));
  }
}
