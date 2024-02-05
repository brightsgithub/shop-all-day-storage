package com.shopallday.storage.infra.initializers.cache;

import com.shopallday.storage.domain.initializers.StorageInitializer;

public class CacheInitializer implements StorageInitializer {
    @Override
    public void initialize() {
        System.out.println("CacheInitializer called");
    }
}
