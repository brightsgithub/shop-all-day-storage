package com.shopallday.storage.infra;

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

    //@Bean
    public LocalEntityManagerFactoryBean entityManagerFactory(Environment environment) {



        LocalEntityManagerFactoryBean emf = new LocalEntityManagerFactoryBean();
        emf.setPersistenceUnitName(null); // Replace with actual name
        // Set DataSource properties from application.properties
        Properties props = new Properties();
        props.put("javax.persistence.jdbc.url", getProperty(environment,"spring.datasource.url"));
        props.put("javax.persistence.jdbc.username", getProperty(environment,"spring.datasource.username"));
        props.put("javax.persistence.jdbc.password", getProperty(environment,"spring.datasource.password"));
        props.put("javax.persistence.jdbc.driver", getProperty(environment,"spring.datasource.driver-class-name"));

        // Optional: Add other hibernate properties if needed
        props.put("hibernate.hbm2ddl.auto", "create"); // Adjust based on your preference

        emf.setJpaProperties(props);

        return emf;
    }

    private String getProperty(Environment environment, String key) {
        return environment.getProperty(key);
    }
}
