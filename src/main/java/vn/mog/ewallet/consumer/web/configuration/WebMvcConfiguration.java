package vn.mog.ewallet.consumer.web.configuration;

import java.util.Locale;
import javax.servlet.MultipartConfigElement;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.resource.VersionResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import vn.mog.ewallet.consumer.web.filters.AuthenticationInterceptor;

@Configuration
@EnableWebMvc
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

  private static String VERSION = "1.0";

  @Bean
  @ConditionalOnMissingBean(RequestContextListener.class)
  public RequestContextListener requestContextListener() {
    return new RequestContextListener();
  }

  @Bean
  public ViewResolver getViewResolver() {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/WEB-INF/templates");
    resolver.setSuffix(".jsp");
    return resolver;
  }

  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }

  // Maps resources path to webapp/resources
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    VersionResourceResolver vrr = new VersionResourceResolver().addFixedVersionStrategy(VERSION, "/**");

    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");

    registry
        .addResourceHandler("/favicon.ico", "/assets/**")
        .addResourceLocations("/", "/assets/")
        .setCachePeriod(21600);

    registry.addResourceHandler("/static/**")
        .addResourceLocations("classpath:/static/")
        .setCachePeriod(60 * 60 * 24 * 365) /* one year */
        .resourceChain(true)
        .addResolver(vrr);
  }

  /******** i18n *********/
  @Bean
  public LocaleResolver localeResolver() {
    SessionLocaleResolver localeResolver = new SessionLocaleResolver();
    localeResolver.setDefaultLocale(new Locale("vi"));
    return  localeResolver;
//    CookieLocaleResolver clr = new CookieLocaleResolver();
//    clr.setDefaultLocale(new Locale("vi"));
//    clr.setCookieName("eWallet");
//    clr.setCookieMaxAge(43200);
//    clr.setCookieSecure(true);
//    clr.setCookieHttpOnly(true);
//    return clr;
  }

  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
    LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
    lci.setParamName("lang");
    return lci;
  }


  @Bean
  public ReloadableResourceBundleMessageSource messageSource() {
    ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
    source.setBasenames("classpath:i18n/common", "classpath:i18n/messages", "classpath:i18n/language");  // name of the resource bundle
    source.setUseCodeAsDefaultMessage(true);
    source.setCacheSeconds(21600);
    source.setDefaultEncoding("UTF-8");
    return source;
  }

  @Bean
  MultipartConfigElement multipartConfigElement() {
    MultipartConfigFactory factory = new MultipartConfigFactory();
    factory.setMaxFileSize("5MB");
    factory.setMaxRequestSize("5MB");
    return factory.createMultipartConfig();
  }

  @Bean
  public AuthenticationInterceptor autenticationInterceptor() {
    return new AuthenticationInterceptor();
  }

  @Bean
  public HttpSessionEventPublisher httpSessionEventPublisher() {
    return new HttpSessionEventPublisher();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    super.addInterceptors(registry);
    registry.addInterceptor(localeChangeInterceptor());
    registry.addInterceptor(autenticationInterceptor());
  }
}
