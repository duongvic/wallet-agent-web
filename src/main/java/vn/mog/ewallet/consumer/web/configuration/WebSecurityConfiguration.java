package vn.mog.ewallet.consumer.web.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import vn.mog.ewallet.consumer.web.security.authentication.CustomAuthenticationEntryPoint;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.bean.TypicalResendOtp;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  private static String[] ROLE_CONFIG = new String[]{"STAFF", "MERCHANT", "CUSTOMER" , "AGENT"};

  @Autowired
  AuthenticationProvider customAuthenticationProvider;

  @Autowired
  AuthenticationSuccessHandler customAuthenticationSuccessHandler;

  @Autowired
  AuthenticationFailureHandler customAuthenticationFailureHandler;

  @Autowired
  CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.eraseCredentials(false).authenticationProvider(customAuthenticationProvider);
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring()
        .antMatchers(HttpMethod.GET, "/", "/*.html", "/**/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js", "/**/*.json",
            "/**/*.js.map", "/**/*.png", "/**/*.jpg", "/**/*.svg", "/**/*.ttf", "/**/*.woff", "/**/*.woff2")
        .antMatchers("/resources/**", "/error/**");
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {

    //CSP (Content Security Policy)
    httpSecurity
        .headers()
        .addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy",
            "script-src 'self' www.google-analytics.com",
            "font-src 'self' https://fonts.googleapis.com"));
    //TODO, Khi trien khai product, add them

    httpSecurity.headers().xssProtection();

    //Content Type Options
    httpSecurity.headers().contentTypeOptions();

    //HTTP Strict Transport Security (HSTS)
    //httpSecurity.headers().httpStrictTransportSecurity();

    //X-Frame-Options
    httpSecurity.headers().frameOptions();

    // don't create session
    /*httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);*/
    httpSecurity.sessionManagement().maximumSessions(2);
    httpSecurity.sessionManagement().invalidSessionUrl("/logout");

    /*Session Fixation Protection with Spring Security*/
    httpSecurity.sessionManagement().sessionFixation().migrateSession();

    /*disable csrf*/
    /*httpSecurity.csrf().disable();*/

    httpSecurity.authorizeRequests()
        .antMatchers("/forgot-password", "/forgot-password/*", "/login/**",
            "/change-default-password",
            "/ajax-controller/customer/security/resend-otp/"
                + String.valueOf(TypicalResendOtp.FORGOT_PASSWORD.getCode()) + "/*").permitAll()
        .anyRequest().authenticated();

    httpSecurity
        .exceptionHandling()
      //  .authenticationEntryPoint(customAuthenticationEntryPoint)
        .accessDeniedPage("/access_denied")
        .and()
        .formLogin()
        .loginPage("/login")
        .loginProcessingUrl("/login.html")
        .usernameParameter("username")
        .passwordParameter("password")
        .successHandler(customAuthenticationSuccessHandler)
        .failureHandler(customAuthenticationFailureHandler)
        .permitAll()

        .and()
        .logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutUrl("/logout")
        .logoutSuccessUrl("/logout.html")
        .deleteCookies("JSESSIONID")
        .clearAuthentication(true)
        .invalidateHttpSession(true).permitAll();
        /*.and()
        .exceptionHandling()
        .accessDeniedHandler(accessDeniedExceptionHandler);*/

    // Custom JWT based security filter
//  httpSecurity.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//  httpSecurity.addFilterBefore(xssFilter, UsernamePasswordAuthenticationFilter.class);

    // disable page caching
    httpSecurity.headers().cacheControl();
  }
}
