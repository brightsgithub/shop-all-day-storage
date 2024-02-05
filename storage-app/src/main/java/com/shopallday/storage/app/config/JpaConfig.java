package com.shopallday.storage.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.shopallday.storage.domain.repository"})
public class JpaConfig {
}
