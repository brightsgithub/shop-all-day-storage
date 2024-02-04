package com.shopallday.storage.app.config;

import com.shopallday.storage.domain.repository.SampleRepo;
import com.shopallday.storage.domain.usecases.SampleUseCase;
import com.shopallday.storage.infra.repository.SampleRepoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfig {

    @Bean
    public SampleUseCase getSampleUseCase(SampleRepo sampleRepo) {
        return new SampleUseCase(sampleRepo);
    }
}
