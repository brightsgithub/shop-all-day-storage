package com.shopallday.storage.app.config;

import com.shopallday.storage.domain.initializers.StorageInitializer;
import com.shopallday.storage.domain.repository.SampleRepo;
import com.shopallday.storage.domain.usecases.CreateCategoryUseCase;
import com.shopallday.storage.domain.usecases.CreateCustomersUseCase;
import com.shopallday.storage.domain.usecases.GetAllCustomersUseCase;
import com.shopallday.storage.domain.usecases.GetCategoryUseCase;
import com.shopallday.storage.infra.initializers.cache.CacheInitializer;
import com.shopallday.storage.infra.initializers.data.CategoryData;
import com.shopallday.storage.infra.initializers.data.CustomerData;
import com.shopallday.storage.infra.initializers.data.DataInitializer;
import com.shopallday.storage.infra.initializers.data.ProductData;
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
            ProductData productData,
            CustomerData customerData,
            CategoryData categoryData
    ) {
        return new DataInitializer(productData, customerData, categoryData);
    }
    @Bean(name = "getCacheInitializer")
    public StorageInitializer getCacheInitializer() {
        return new CacheInitializer();
    }

    @Bean
    public CustomerData getCustomerData(
            CreateCustomersUseCase createCustomersUseCase,
            GetAllCustomersUseCase getAllCustomersUseCase) {
        return new CustomerData(createCustomersUseCase, getAllCustomersUseCase);
    }

    @Bean
    public ProductData getProductData() {
        return new ProductData();
    }

    @Bean
    public CategoryData getCategoryData(
            CreateCategoryUseCase createCategoryUseCase,
            GetCategoryUseCase getCategoryUseCase
    ) {
        return new CategoryData(createCategoryUseCase, getCategoryUseCase);
    }
}
