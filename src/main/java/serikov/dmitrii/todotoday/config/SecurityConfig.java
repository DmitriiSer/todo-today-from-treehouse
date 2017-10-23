package serikov.dmitrii.todotoday.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.query.spi.EvaluationContextExtension;
import org.springframework.data.repository.query.spi.EvaluationContextExtensionSupport;
import org.springframework.data.repository.query.spi.Function;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.util.Map;
import serikov.dmitrii.todotoday.model.User;
import serikov.dmitrii.todotoday.service.UserService;
import serikov.dmitrii.todotoday.web.FlashMessage;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserService userService;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService)
        .passwordEncoder(passwordEncoder());
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring()
        .antMatchers("/static/**")
        .antMatchers("/css/**")
        .antMatchers("/js/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        // authorize any requests that have role ROLE_USER
        .anyRequest().hasRole("USER")
        // login handlers
        .and()
        .formLogin().loginPage("/login").permitAll()
        .successHandler(loginSuccessHandler())
        .failureHandler(loginFailureHandler())
        // logout handler
        .and()
        .logout().permitAll().logoutSuccessUrl("/login")
        // adding CSRF protection
        .and().csrf();
  }

  private AuthenticationSuccessHandler loginSuccessHandler() {
    return (request, response, authentication) -> response.sendRedirect("/");
  }

  private AuthenticationFailureHandler loginFailureHandler() {
    return (request, response, authentication) -> {
      // preserve existing user data
      request.getSession().setAttribute("user",
          new User(request.getParameter("username"),
              request.getParameter("password")));
      // set the failure message
      request.getSession().setAttribute("flash",
          new FlashMessage("Incorrect username or password. Please try again.",
              FlashMessage.Status.FAILURE));
      response.sendRedirect("/login");
    };
  }

  @Bean
  public EvaluationContextExtension securityExtension() {
    return new EvaluationContextExtensionSupport() {
      @Override
      public String getExtensionId() {
        return "security";
      }

      @Override
      public Object getRootObject() {
        Authentication authentication = SecurityContextHolder
            .getContext()
            .getAuthentication();
        return new SecurityExpressionRoot(authentication) {
        };
      }

    };
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(10);
  }

}
