package com.shopallday.storage.infra.repository;

import com.shopallday.storage.domain.repository.RepositoryManager;
import jakarta.persistence.EntityManager;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@PropertySource("classpath:test-storage-infra-application.properties") // Run against a h2 in memory db instead
@EnableJpaRepositories(
        basePackages = {"com.shopallday.storage.domain.repository"}
)
@EntityScan(basePackages = {"com.shopallday.storage.infra.entities"})
public class JPAConfigForTest {
    @Bean
    public RepositoryManager getEntityManagerWrapper(EntityManager manager) {
        return new RepositoryManager(manager);
    }
}
