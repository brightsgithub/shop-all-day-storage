package com.shopallday.storage.app.config;

import com.shopallday.storage.app.initializers.AppInitializer;
import com.shopallday.storage.domain.initializers.StorageInitializer;
import com.shopallday.storage.domain.repository.SampleRepo;
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
    public StorageInitializer getDataInitializer() {
        return new DataInitializer();
    }
    @Bean(name = "getCacheInitializer")
    public StorageInitializer getCacheInitializer() {
        return new CacheInitializer();
    }
}
