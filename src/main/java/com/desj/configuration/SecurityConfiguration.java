package com.desj.configuration;

/**
 * Created by Julien on 22.04.16.
 * <p>
 * Configuration for spring security. This allows user to access the front page "/",
 * but no other site without a password.
 * WARNING! We must remove the access of our database ("/console/**") for production,
 * but for now it's quite useful!
 */

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers("/console/**").permitAll().anyRequest().fullyAuthenticated()
                .and().formLogin().loginPage("/login").permitAll()
                .and().logout().permitAll();

        httpSecurity.headers().frameOptions().disable();
    }

    @Bean
    public JdbcUserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager detailsManager = new JdbcUserDetailsManager();
        detailsManager.setDataSource(dataSource);
        return detailsManager;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder,
                                JdbcUserDetailsManager detailsManager, DataSource dataSource,
                                BCryptPasswordEncoder encoder) throws Exception {
        authenticationManagerBuilder.userDetailsService(detailsManager);
        JdbcUserDetailsManagerConfigurer<AuthenticationManagerBuilder> configurer = new JdbcUserDetailsManagerConfigurer<>(detailsManager);
        authenticationManagerBuilder.apply(configurer);
        configurer.dataSource(dataSource).withDefaultSchema().passwordEncoder(encoder);
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}