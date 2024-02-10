package com.shopallday.storage.app.config;

import com.shopallday.storage.domain.initializers.StorageInitializer;
import com.shopallday.storage.domain.usecases.*;
import com.shopallday.storage.infra.initializers.cache.CacheInitializer;
import com.shopallday.storage.infra.initializers.data.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfraConfig {


    @Bean(name = "getDataInitializer")
    public StorageInitializer getDataInitializer(
            @Qualifier("getProductData") DataHelper productData,
            @Qualifier("getCustomerData") DataHelper customerData,
            @Qualifier("getCategoryData") DataHelper categoryData,
            @Qualifier("getProductTypeData") DataHelper productTypeData,
            @Qualifier("getBrandData") DataHelper brandData
    ) {
        return new DataInitializer(productData, customerData, categoryData, productTypeData, brandData);
    }
    @Bean(name = "getCacheInitializer")
    public StorageInitializer getCacheInitializer() {
        return new CacheInitializer();
    }

    @Bean(name = "getCustomerData")
    public DataHelper getCustomerData(
            CreateCustomersUseCase createCustomersUseCase,
            GetAllCustomersUseCase getAllCustomersUseCase) {
        return new CustomerData(createCustomersUseCase, getAllCustomersUseCase);
    }

    @Bean(name = "getProductData")
    public DataHelper getProductData(
            CreateProductsUseCase createProductsUseCase,
            GetAllProductsUseCase getAllProductsUseCase,
            GetAllProductTypesUseCase getAllProductTypesUseCase,
            GetAllBrandsUseCase getAllBrandsUseCase
    ) {
        return new ProductData(createProductsUseCase, getAllProductsUseCase, getAllProductTypesUseCase, getAllBrandsUseCase);
    }

    @Bean(name = "getCategoryData")
    public DataHelper getCategoryData(
            CreateCategoryUseCase createCategoryUseCase,
            GetCategoryUseCase getCategoryUseCase
    ) {
        return new CategoryData(createCategoryUseCase, getCategoryUseCase);
    }

    @Bean(name = "getProductTypeData")
    public DataHelper getProductTypeData(
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
    @Bean(name = "getBrandData")
    public DataHelper getBrandData(
            GetAllBrandsUseCase getAllBrandsUseCase,
            CreateBrandsUseCase createBrandsUseCase
    ) {
        return new BrandData(getAllBrandsUseCase, createBrandsUseCase);
    }
}
