package com.shopallday.storage.app.config;

import com.shopallday.storage.domain.repository.*;
import com.shopallday.storage.domain.usecases.*;
import com.shopallday.storage.infra.repository.SampleRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfig {

    @Bean
    public SampleUseCase getSampleUseCase(SampleRepo sampleRepo) {
        return new SampleUseCase(sampleRepo);
    }
    @Bean
    public CreateCustomersUseCase getCreateCustomersUseCase(CustomerRepository customerRepository) {
        return new CreateCustomersUseCase(customerRepository);
    }
    @Bean
    public GetAllCustomersUseCase getGetAllCustomersUseCase(CustomerRepository customerRepository) {
        return new GetAllCustomersUseCase(customerRepository);
    }
    @Bean
    public CreateCategoryUseCase getCreateCategoryUseCase(CategoryRepository categoryRepository) {
        return new CreateCategoryUseCase(categoryRepository);
    }

    @Bean
    public GetCategoryUseCase getGetCategoryUseCase(CategoryRepository categoryRepository) {
        return new GetCategoryUseCase(categoryRepository);
    }

    @Bean
    public CreateProductTypeUseCase getCreateProductTypeUseCase(ProductTypeRepository productTypeRepository) {
        return new CreateProductTypeUseCase(productTypeRepository);
    }

    @Bean
    public GetAllProductTypesUseCase getGetAllProductTypesUseCase(ProductTypeRepository productTypeRepository) {
        return new GetAllProductTypesUseCase(productTypeRepository);
    }

    @Bean
    public GetAllBrandsUseCase getGetAllBrandsUseCase(BrandRepository brandRepository) {
        return new GetAllBrandsUseCase(brandRepository);
    }

    @Bean
    public CreateBrandsUseCase getCreateBrandsUseCase(BrandRepository brandRepository) {
        return new CreateBrandsUseCase(brandRepository);
    }

    @Bean
    public CreateProductsUseCase getCreateProductsUseCase(ProductsRepository productsRepository) {
        return new CreateProductsUseCase(productsRepository);
    }

    @Bean
    public GetAllProductsUseCase getGetAllProductsUseCase(ProductsRepository productsRepository) {
        return new GetAllProductsUseCase(productsRepository);
    }
}
