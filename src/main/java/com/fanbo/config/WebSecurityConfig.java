package com.fanbo.config;

import com.fanbo.authentication.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private WebAuthenticationSuccessHandler authSeccessHandler;
    @Autowired
    private WebAuthenticationFailureHandler authFailureHandler;
    @Autowired
    private WebLogoutHandler logoutHandler;
    @Autowired
    private WebLogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;
    @Autowired
    private WebAuthenticationAccessDeniedHandler webAuthenticationAccessDeniedHandler;
    @Autowired
    private WebAuthenticationEntryPoint webAuthenticationEntryPoint;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.POST, "/logout").authenticated()
                .antMatchers("/hello/**", "/swagger2/**", "/user/**", "/websocket/**", "/v2/**", "/scvpn.exe").permitAll()    //无需认证的
                .antMatchers("/api2/**").authenticated().and()                             //需认证的

                .formLogin()
                .successHandler(authSeccessHandler)
                .failureHandler(authFailureHandler)
                .loginProcessingUrl("/login").and()
                .logout().addLogoutHandler(logoutHandler)
                .logoutSuccessHandler(logoutSuccessHandler)
                .logoutSuccessUrl("/logout").and()
                .sessionManagement().maximumSessions(10).and()  //session最大连接数量
                .invalidSessionUrl("/login");   // session失效跳转路径

        http.
                exceptionHandling().
                authenticationEntryPoint(webAuthenticationEntryPoint).
                accessDeniedHandler(webAuthenticationAccessDeniedHandler).
                and().headers().frameOptions().disable();

    }


    @Bean
    public CustomAuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider();
    }

    @Bean
    public WebAuthenticationSuccessHandler webAuthenticationSuccessHandler() {
        return new WebAuthenticationSuccessHandler();
    }

    @Bean
    public WebAuthenticationFailureHandler webAuthenticationFailureHandler() {
        return new WebAuthenticationFailureHandler();
    }

    @Bean
    public WebLogoutSuccessHandler logoutSuccessHandler() {
        return new WebLogoutSuccessHandler();
    }

    @Bean
    public WebLogoutHandler logoutHandler() {
        return new WebLogoutHandler();
    }

    @Bean
    public WebAuthenticationAccessDeniedHandler accessDeniedHandler() {
        return new WebAuthenticationAccessDeniedHandler();
    }
//
    @Bean
    public WebAuthenticationEntryPoint webAuthenticationEntryPoint() {
        return new WebAuthenticationEntryPoint();
    }
//    }

}
