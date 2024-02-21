package com.shopallday.storage.app;

import com.shopallday.storage.infra.InfraConfig;
import com.shopallday.storage.infra.JpaConfig;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {JpaConfig.class, InfraConfig.class})
@PropertySource("classpath:storage-infra-application.properties")
@ComponentScan(basePackages = {"com.shopallday.*"})
@EntityScan(basePackages = {"com.shopallday.storage.infra.entities"})
@Log
public class StorageAppApplication implements CommandLineRunner {

    private final DataSource dataSource;

    StorageAppApplication(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static void main(String[] args) {
        SpringApplication.run(StorageAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Datasource " + dataSource.toString());
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("select 1");
    }
}
