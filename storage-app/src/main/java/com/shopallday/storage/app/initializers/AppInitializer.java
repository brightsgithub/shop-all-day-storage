package com.shopallday.storage.app.initializers;

import com.shopallday.storage.domain.initializers.StorageInitializer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AppInitializer implements StorageInitializer {

    private final StorageInitializer dataInitializer;
    private final StorageInitializer cacheInitializer;

    public AppInitializer(
            @Qualifier("getDataInitializer") StorageInitializer dataInitializer,
            @Qualifier("getCacheInitializer") StorageInitializer cacheInitializer) {
        this.dataInitializer = dataInitializer;
        this.cacheInitializer = cacheInitializer;
    }

    @PostConstruct
    @Override
    public void initialize() throws Exception{
        dataInitializer.initialize();
        cacheInitializer.initialize();
    }
}
