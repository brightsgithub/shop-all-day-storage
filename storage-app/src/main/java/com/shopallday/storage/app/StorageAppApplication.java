package com.shopallday.storage.app;

//import com.shopallday.storage.domain.usecases.SampleUseCase;
import com.shopallday.storage.domain.repository.SampleRepo;
import com.shopallday.storage.domain.usecases.SampleUseCase;
import com.shopallday.storage.infra.repository.SampleRepoImpl;
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
@EnableJpaRepositories(basePackages = {"com.shopallday.*"})
@PropertySource("classpath:storage-infra-application.properties")
@ComponentScan(basePackages = {"com.shopallday.*"})
@EntityScan(basePackages = {"com.shopallday.*"})
@Log
public class StorageAppApplication implements CommandLineRunner {

    private final DataSource dataSource;
    private final SampleUseCase sampleUseCase;
    private final SampleRepo sampleRepo;

    StorageAppApplication(DataSource dataSource, SampleUseCase sampleUseCase, SampleRepo sampleRepo) {
        this.sampleUseCase = sampleUseCase;
        this.dataSource = dataSource;
        this.sampleRepo = sampleRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(StorageAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(sampleUseCase.execute());
        System.out.println(sampleRepo.execute());
        log.info("Datasource " + dataSource.toString());
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("select 1");
    }
}
