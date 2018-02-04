package com.zooplus.sdc.converter.config;

import com.zooplus.sdc.converter.security.CustomAuthenticationSuccessHandler;
import com.zooplus.sdc.converter.security.UserNameAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Bean
    public AuthenticationProvider userNameAuthProvider() {
        return new UserNameAuthenticationProvider();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/webjars/**", "/static/**", "/swagger-resources/**", "/swagger-ui.html", "/v2/api-docs").permitAll()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/", "/users/signup").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint())
                .and()
                .formLogin()
                .loginPage("/login")
                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
                .successHandler(authenticationSuccessHandler)
                .usernameParameter("userName").passwordParameter("password")
                .permitAll()
                .and()
                .logout().deleteCookies("SESSION")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/swagger-ui.html")
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(userNameAuthProvider());
    }

    @Profile("!dev")
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }


    @Profile("dev")
    @Configuration
    public class DevSecurityConfiguration {

        @Bean
        public AuthenticationSuccessHandler authenticationSuccessHandler() {
            class CorsAuthenticationSuccessHandler extends CustomAuthenticationSuccessHandler {

                @Override
                public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                    super.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);
                    httpServletResponse.setHeader("Access-Control-Allow-Credentials", Boolean.TRUE.toString());
                    httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,HEAD,POST");
                    httpServletResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
                }
            }
            return new CorsAuthenticationSuccessHandler();
        }

        @Bean
        public WebMvcConfigurer corsConfigurer() {

            return new WebMvcConfigurerAdapter() {
                @Override
                public void addCorsMappings(CorsRegistry registry) {
                    registry.addMapping("/**")
                            .allowedOrigins("*");
                }
            };
        }
    }

}