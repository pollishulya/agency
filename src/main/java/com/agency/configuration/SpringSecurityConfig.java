package com.agency.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//
//@Slf4j
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
//public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
//    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth
//                .userDetailsService(userDetailsService);
//
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web
//                .ignoring()
//                .antMatchers("/resources/**", "/static/**", "/webjars/**");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.authorizeRequests()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/").permitAll()
//                .antMatchers("/welcome").permitAll()
//                .antMatchers("/description").permitAll()
//                .antMatchers("/registration").permitAll()
//                .antMatchers(HttpMethod.POST, "/registration").permitAll()
//                .antMatchers("/comment/comments").permitAll()
//                .antMatchers("/tour/{id}").permitAll()
//                .antMatchers("/account/settings").permitAll()
//
//                .antMatchers(HttpMethod.POST, "/account/delete/{id}")
//                .access("hasRole('ROLE_ADMIN')")
//                .antMatchers(HttpMethod.POST, "/account/saveAfterUpdate")
//                .access("hasRole('ROLE_ADMIN')")
//                .antMatchers("/account/findAccounts")
//                .access("hasRole('ROLE_ADMIN')")
//                .antMatchers("/account/update/{id}")
//                .access("hasRole('ROLE_ADMIN')")
//                .antMatchers("/account/showAccounts")
//                .access("hasRole('ROLE_ADMIN')")
//                .antMatchers("/account/roles")
//                .access("hasRole('ROLE_ADMIN')")
//
//                .antMatchers(HttpMethod.POST, "/comment/save")
//                .access("hasRole('ROLE_USER')")
//                .antMatchers(HttpMethod.POST, "/tour/reservation")
//                .access("hasRole('ROLE_USER')")
//
//
//                .antMatchers(HttpMethod.POST, "/showTours")
//                .access("hasRole('ROLE_COMPANY')")
//
//                .and().formLogin()
//                .loginProcessingUrl("/j_spring_security_check")
//                .loginPage("/").failureUrl("/?error")
//                .defaultSuccessUrl("/", true)
//                .usernameParameter("email")
//                .passwordParameter("password")
//                .and().logout().logoutSuccessUrl("/?logout")
//                .and().exceptionHandling().accessDeniedPage("/403")
//                .and().csrf().disable();
//
//    }
//}
//
//
