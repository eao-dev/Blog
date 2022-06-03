package com.blog.Config.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Security configuration.
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${userAccess}")
    private String[] userAccess;

    @Value("${anonymous}")
    private String[] anonymous;

    @Value("${all}")
    private String[] all;

    @Value("${disabledCSRFURL}")
    private String[] disabledCSRFURL;

    @Value("${defaultURL}")
    private String defaultURL;

    @Autowired
    private AuthProvider authProvider;

    /**
     * Authentication manager settings.
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider); // Add custom auth provider
    }

    /**
     * Authorization settings.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers(anonymous).anonymous()
                .antMatchers(userAccess).authenticated()
                .antMatchers(all).permitAll()
                .anyRequest().authenticated().and().formLogin()
                .defaultSuccessUrl(defaultURL)
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and().
                csrf().ignoringAntMatchers(disabledCSRFURL) // Disabled CSRF
        ;
    }

}
