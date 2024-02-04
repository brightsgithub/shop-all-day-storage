package com.shopallday.storage.app.config;

import com.shopallday.storage.domain.repository.SampleRepo;
import com.shopallday.storage.infra.repository.SampleRepoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfraConfig {

    @Bean
    public SampleRepo getSampleRepo() {
        return new SampleRepoImpl();
    }
}
