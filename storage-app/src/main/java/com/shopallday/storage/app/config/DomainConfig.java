package com.shopallday.storage.app.config;

import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.repository.customer.CustomerRepository;
import com.shopallday.storage.domain.repository.customer.CustomerShippingAddRepository;
import com.shopallday.storage.domain.repository.orders.CustomerOrderDetailRepository;
import com.shopallday.storage.domain.repository.orders.OrderLinesRepository;
import com.shopallday.storage.domain.repository.orders.OrderStatusTypeRepository;
import com.shopallday.storage.domain.repository.orders.OrdersRepository;
import com.shopallday.storage.domain.repository.products.*;
import com.shopallday.storage.domain.usecases.customer.*;
import com.shopallday.storage.domain.usecases.orders.*;
import com.shopallday.storage.domain.usecases.products.*;
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

    @Bean
    public CreateCustomerShippingAddressUseCase getCreateCustomerShippingAddressUseCase(
            CustomerShippingAddRepository customerShippingAddRepository,
            RepositoryManager repositoryManager
    ){
        return new CreateCustomerShippingAddressUseCase(customerShippingAddRepository, repositoryManager);
    }

    @Bean
    public GetAllCustomerShippingAddressUseCase getGetAllCustomerShippingAddressUseCase(
            CustomerShippingAddRepository customerShippingAddRepository
    ){
        return new GetAllCustomerShippingAddressUseCase(customerShippingAddRepository);
    }

    @Bean
    public GetCustomerShipAddByIdUseCase getGetCustomerShipAddByIdUseCase(
            CustomerShippingAddRepository customerShippingAddRepository
    ){
        return new GetCustomerShipAddByIdUseCase(customerShippingAddRepository);
    }
    @Bean
    public GetAllOrdersUseCase getGetAllOrdersUseCase(OrdersRepository ordersRepository) {
        return new GetAllOrdersUseCase(ordersRepository);
    }

    @Bean
    public GetOrdersByCustomerIdUseCase getGetOrdersByCustomerIdUseCase(OrdersRepository ordersRepository) {
        return new GetOrdersByCustomerIdUseCase(ordersRepository);
    }
    @Bean
    public GetCustomerOrderDetailUseCase getGetCustomerOrderDetailUseCase(
            CustomerOrderDetailRepository customerOrderDetailRepository
    ) {
        return new GetCustomerOrderDetailUseCase(customerOrderDetailRepository);
    }
    @Bean
    public CreateOrderUseCase getCreateOrderUseCase(
            OrdersRepository ordersRepository,
            RepositoryManager repositoryManager
    ) {
        return new CreateOrderUseCase(ordersRepository, repositoryManager);
    }
    @Bean
    public CreateOrderLineUseCase getCreateOrderLineUseCase(
            OrderLinesRepository orderLinesRepository,
            RepositoryManager repositoryManager
    ) {
        return new CreateOrderLineUseCase(orderLinesRepository, repositoryManager);
    }

    @Bean
    public CreateOrderStatusTypeUseCase getCreateOrderStatusTypeUseCase(
            OrderStatusTypeRepository orderStatusTypeRepository,
            RepositoryManager repositoryManager
    ) {
        return new CreateOrderStatusTypeUseCase(orderStatusTypeRepository, repositoryManager);
    }
    @Bean
    public GetAllOrderStatusTypesUseCase getGetAllOrderStatusTypesUseCase(
            OrderStatusTypeRepository orderStatusTypeRepository
    ) {
        return new GetAllOrderStatusTypesUseCase(orderStatusTypeRepository);
    }
}
