package com.shopallday.storage.infra.initializers.data;

import com.shopallday.storage.domain.initializers.StorageInitializer;

public class DataInitializer implements StorageInitializer {

    @Override
    public void initialize() {
        System.out.println("DataInitializer called");
    }
}
