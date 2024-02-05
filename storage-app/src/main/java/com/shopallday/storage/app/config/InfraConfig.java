package com.shopallday.storage.app.config;

import com.shopallday.storage.domain.initializers.StorageInitializer;
import com.shopallday.storage.domain.repository.SampleRepo;
import com.shopallday.storage.domain.usecases.CreateCustomersUseCase;
import com.shopallday.storage.domain.usecases.GetAllCustomersUseCase;
import com.shopallday.storage.infra.initializers.cache.CacheInitializer;
import com.shopallday.storage.infra.initializers.data.DataInitializer;
import com.shopallday.storage.infra.repository.SampleRepoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfraConfig {

    @Bean
    public SampleRepo getSampleRepo() {
        return new SampleRepoImpl();
    }

    @Bean(name = "getDataInitializer")
    public StorageInitializer getDataInitializer(
            CreateCustomersUseCase createCustomersUseCase, GetAllCustomersUseCase getAllCustomersUseCase) {
        return new DataInitializer(createCustomersUseCase, getAllCustomersUseCase);
    }
    @Bean(name = "getCacheInitializer")
    public StorageInitializer getCacheInitializer() {
        return new CacheInitializer();
    }
}
