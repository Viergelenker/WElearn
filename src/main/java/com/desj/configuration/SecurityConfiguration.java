package com.desj.configuration;

/**
 * Created by Julien on 22.04.16.
 * <p>
 * Configuration for spring security. This allows user to access the front page "/",
 * but no other site without a password.
 * WARNING! We must remove the access of our database ("/console/**") for production,
 * but for now it's quite useful!
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers("/").permitAll().and()
                .authorizeRequests().antMatchers("/console/**").permitAll();

        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
    }
}