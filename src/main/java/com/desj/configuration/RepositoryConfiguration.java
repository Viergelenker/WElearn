package com.desj.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Julien on 23.04.16.
 */

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.desj.model"})
@EnableJpaRepositories(basePackages = {"com.desj.model"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
