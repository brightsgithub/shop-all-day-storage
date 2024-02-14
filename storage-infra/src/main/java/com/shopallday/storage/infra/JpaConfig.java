package com.shopallday.storage.infra;

import com.shopallday.storage.domain.repository.RepositoryManager;
import jakarta.persistence.EntityManager;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

import java.util.Properties;

@Configuration
@PropertySource("classpath:storage-infra-application.properties")
@EnableJpaRepositories(
        basePackages = {"com.shopallday.storage.domain.repository"}
)
@EntityScan(basePackages = {"com.shopallday.storage.infra.entities"})
//@ComponentScan(basePackages = {"com.shopallday.*"})
public class JpaConfig {
    @Bean
    public RepositoryManager getEntityManagerWrapper(EntityManager manager) {
        return new RepositoryManager(manager);
    }
}
