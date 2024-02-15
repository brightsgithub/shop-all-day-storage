package com.shopallday.storage.app.config;

import com.shopallday.storage.domain.repository.*;
import com.shopallday.storage.domain.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan(basePackages = {"com.shopallday.storage.infra"})
//@ComponentScan(basePackages = {"com.shopallday.*"})
public class DomainConfig {

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
    public CreateProductTypeUseCase getCreateProductTypeUseCase(
            ProductTypeRepository productTypeRepository, RepositoryManager repositoryManager) {
        return new CreateProductTypeUseCase(productTypeRepository, repositoryManager);
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
    public CreateProductsUseCase getCreateProductsUseCase(
            ProductsRepository productsRepository,
            RepositoryManager repositoryManager
    ) {
        return new CreateProductsUseCase(productsRepository, repositoryManager);
    }

    @Bean
    public GetAllProductsUseCase getGetAllProductsUseCase(ProductsRepository productsRepository) {
        return new GetAllProductsUseCase(productsRepository);
    }
    @Bean
    public CreateProductStockUseCase getCreateProductStockUseCase(
            ProductStockRepository productStockRepository,
            RepositoryManager repositoryManager
            ) {
        return new CreateProductStockUseCase(productStockRepository, repositoryManager);
    }

    @Bean
    public GetAllProductStockUseCase getGetAllProductStockUseCase(ProductStockRepository productStockRepository) {
        return new GetAllProductStockUseCase(productStockRepository);
    }
}
