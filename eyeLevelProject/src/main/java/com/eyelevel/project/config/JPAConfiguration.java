package com.eyelevel.project.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"com.eyelevel.project"})
@EnableJpaRepositories(basePackages = "com.eyelevel.project")
public class JPAConfiguration {

}