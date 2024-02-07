package com.shopallday.storage.app.config;

import com.shopallday.storage.domain.initializers.StorageInitializer;
import com.shopallday.storage.domain.repository.SampleRepo;
import com.shopallday.storage.domain.usecases.*;
import com.shopallday.storage.infra.initializers.cache.CacheInitializer;
import com.shopallday.storage.infra.initializers.data.*;
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
            CategoryData categoryData,
            ProductTypeData productTypeData
    ) {
        return new DataInitializer(productData, customerData, categoryData, productTypeData);
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

    @Bean
    public ProductTypeData getProductTypeData(
            GetCategoryUseCase getCategoryUseCase,
            CreateProductTypeUseCase createProductTypeUseCase,
            GetAllProductTypesUseCase getAllProductTypesUseCase
    ) {
        return new ProductTypeData(
                getCategoryUseCase,
                createProductTypeUseCase,
                getAllProductTypesUseCase
        );
    }
}
