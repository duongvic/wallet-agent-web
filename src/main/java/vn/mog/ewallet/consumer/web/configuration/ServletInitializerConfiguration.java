package vn.mog.ewallet.consumer.web.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import vn.mog.ewallet.consumer.web.ConsumerApplication;

public class ServletInitializerConfiguration extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    //set register error pagefilter false
    setRegisterErrorPageFilter(false);
    return builder.sources(ConsumerApplication.class);
  }

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    super.onStartup(servletContext);
    servletContext.setInitParameter("defaultHtmlEscape", "true");
  }
}
