package com.shopallday.storage.app.config;

import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.repository.customer.CustomerRepository;
import com.shopallday.storage.domain.repository.customer.CustomerShippingAddRepository;
import com.shopallday.storage.domain.repository.orders.CustomerOrderDetailRepository;
import com.shopallday.storage.domain.repository.orders.OrderLinesRepository;
import com.shopallday.storage.domain.repository.orders.OrderStatusTypeRepository;
import com.shopallday.storage.domain.repository.orders.OrdersRepository;
import com.shopallday.storage.domain.repository.products.*;
import com.shopallday.storage.domain.usecases.brand.*;
import com.shopallday.storage.domain.usecases.category.*;
import com.shopallday.storage.domain.usecases.customer.*;
import com.shopallday.storage.domain.usecases.orders.*;
import com.shopallday.storage.domain.usecases.orderstatustype.*;
import com.shopallday.storage.domain.usecases.products.*;
import com.shopallday.storage.domain.usecases.productstock.*;
import com.shopallday.storage.domain.usecases.producttype.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfig {

    @Bean
    public CreateCustomersUseCase getCreateCustomersUseCase(CustomerRepository customerRepository) {
        return new CreateCustomersUseCase(customerRepository);
    }
    @Bean
    public CreateSingleCustomerUseCase getCreateSingleCustomerUseCase(CustomerRepository customerRepository) {
        return new CreateSingleCustomerUseCase(customerRepository);
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
    public GetCategoryByIdUseCase getGetCategoryByIdUseCase(CategoryRepository categoryRepository) {
        return new GetCategoryByIdUseCase(categoryRepository);
    }
    @Bean
    public UpdateCategoryUseCase getUpdateCategoryUseCase(CategoryRepository categoryRepository) {
        return new UpdateCategoryUseCase(categoryRepository);
    }
    @Bean
    public DeleteCategoryUseCase getDeleteCategoryUseCase(CategoryRepository categoryRepository) {
        return new DeleteCategoryUseCase(categoryRepository);
    }

    @Bean
    public GetCategoryUseCase getGetCategoryUseCase(CategoryRepository categoryRepository) {
        return new GetCategoryUseCase(categoryRepository);
    }
    @Bean
    public CreateSingleCategoryUseCase getCreateSingleCategoryUseCase(CategoryRepository categoryRepository) {
        return new CreateSingleCategoryUseCase(categoryRepository);
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
    public CreateSingleProductTypeUseCase getCreateSingleProductTypeUseCase(
            ProductTypeRepository productTypeRepository, RepositoryManager repositoryManager) {
        return new CreateSingleProductTypeUseCase(productTypeRepository, repositoryManager);
    }
    @Bean
    public DeleteProductTypeUseCase getDeleteProductTypeUseCase(
            ProductTypeRepository productTypeRepository) {
        return new DeleteProductTypeUseCase(productTypeRepository);
    }
    @Bean
    public UpdateProductTypeUseCase getUpdateProductTypeUseCase(
            ProductTypeRepository productTypeRepository, RepositoryManager repositoryManager) {
        return new UpdateProductTypeUseCase(productTypeRepository, repositoryManager);
    }
    @Bean
    public GetProductTypeByIdUseCase getGetProductTypeByIdUseCase(ProductTypeRepository productTypeRepository) {
        return new GetProductTypeByIdUseCase(productTypeRepository);
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
    public CreateSingleBrandUseCase getGetSingleBrandUseCase(BrandRepository brandRepository) {
        return new CreateSingleBrandUseCase(brandRepository);
    }
    @Bean
    public UpdateBrandsUseCase getUpdateBrandsUseCase(BrandRepository brandRepository) {
        return new UpdateBrandsUseCase(brandRepository);
    }
    @Bean
    public DeleteBrandsUseCase getDeleteBrandsUseCase(BrandRepository brandRepository) {
        return new DeleteBrandsUseCase(brandRepository);
    }
    @Bean
    public GetBrandByIdUseCase getGetBrandByIdUseCase(BrandRepository brandRepository) {
        return new GetBrandByIdUseCase(brandRepository);
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
    public CreateSingleProductStockUseCase getCreateSingleProductStockUseCase(
            ProductStockRepository productStockRepository,
            RepositoryManager repositoryManager
            ) {
        return new CreateSingleProductStockUseCase(productStockRepository, repositoryManager);
    }
    @Bean
    public UpdateProductStockUseCase getUpdateProductStockUseCase(
            ProductStockRepository productStockRepository,
            RepositoryManager repositoryManager
    ) {
        return new UpdateProductStockUseCase(productStockRepository, repositoryManager);
    }
    @Bean
    public DeleteProductStockUseCase getDeleteProductStockUseCase(
            ProductStockRepository productStockRepository
    ) {
        return new DeleteProductStockUseCase(productStockRepository);
    }
    @Bean
    public GetProductStockByIdUseCase getGetProductStockByIdUseCase(
            ProductStockRepository productStockRepository
    ) {
        return new GetProductStockByIdUseCase(productStockRepository);
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
    public CreateSingleOrderUseCase getCreateSingleOrderUseCase(
            OrdersRepository ordersRepository,
            RepositoryManager repositoryManager
    ) {
        return new CreateSingleOrderUseCase(ordersRepository, repositoryManager);
    }
    @Bean
    public DeleteOrderUseCase getDeleteOrderUseCase(
            OrdersRepository ordersRepository
    ) {
        return new DeleteOrderUseCase(ordersRepository);
    }
    @Bean
    public GetOrderByIdUseCase getGetOrderByIdUseCase(
            OrdersRepository ordersRepository
    ) {
        return new GetOrderByIdUseCase(ordersRepository);
    }
    @Bean
    public UpdateOrderUseCase getUpdateOrderUseCase(
            OrdersRepository ordersRepository,
            RepositoryManager repositoryManager
    ) {
        return new UpdateOrderUseCase(ordersRepository, repositoryManager);
    }

    @Bean
    public CreateOrderLineUseCase getCreateOrderLineUseCase(
            OrderLinesRepository orderLinesRepository,
            RepositoryManager repositoryManager
    ) {
        return new CreateOrderLineUseCase(orderLinesRepository, repositoryManager);
    }
    @Bean
    public CreateSingleOrderLineUseCase getCreateSingleOrderLineUseCase(
            OrderLinesRepository orderLinesRepository,
            RepositoryManager repositoryManager
    ) {
        return new CreateSingleOrderLineUseCase(orderLinesRepository, repositoryManager);
    }
    @Bean
    public DeleteSingleOrderLineUseCase getDeleteSingleOrderLineUseCase(
            OrderLinesRepository orderLinesRepository
    ) {
        return new DeleteSingleOrderLineUseCase(orderLinesRepository);
    }
    @Bean
    public GetOrderLineByIdUseCase getGetOrderLineByIdUseCase(
            OrderLinesRepository orderLinesRepository
    ) {
        return new GetOrderLineByIdUseCase(orderLinesRepository);
    }
    @Bean
    public GetAllOrderLinesUseCase getGetAllOrderLinesUseCase(
            OrderLinesRepository orderLinesRepository
    ) {
        return new GetAllOrderLinesUseCase(orderLinesRepository);
    }
    @Bean
    public UpdateOrderLineUseCase getUpdateOrderLineUseCase(
            OrderLinesRepository orderLinesRepository,
            RepositoryManager repositoryManager
    ) {
        return new UpdateOrderLineUseCase(orderLinesRepository, repositoryManager);
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
    @Bean
    public CreateSingleOrderStatusTypeUseCase getCreateSingleOrderStatusTypeUseCase(
            OrderStatusTypeRepository orderStatusTypeRepository
    ) {
        return new CreateSingleOrderStatusTypeUseCase(orderStatusTypeRepository);
    }
    @Bean
    public DeleteOrderStatusTypeUseCase getDeleteOrderStatusTypeUseCase(
            OrderStatusTypeRepository orderStatusTypeRepository
    ) {
        return new DeleteOrderStatusTypeUseCase(orderStatusTypeRepository);
    }
    @Bean
    public GetOrderStatusTypeByIdUseCase getGetOrderStatusTypeByIdUseCase(
            OrderStatusTypeRepository orderStatusTypeRepository
    ) {
        return new GetOrderStatusTypeByIdUseCase(orderStatusTypeRepository);
    }
    @Bean
    public UpdateOrderStatusTypeUseCase getUpdateOrderStatusTypeUseCase(
            OrderStatusTypeRepository orderStatusTypeRepository
    ) {
        return new UpdateOrderStatusTypeUseCase(orderStatusTypeRepository);
    }
    @Bean
    public GetCustomersByIdUseCase getGetCustomersByIdUseCase(
            CustomerRepository customerRepository
    ) {
        return new GetCustomersByIdUseCase(customerRepository);
    }
    @Bean
    public DeleteProductUseCase getDeleteProductUseCase(
            ProductsRepository productsRepository
    ) {
        return new DeleteProductUseCase(productsRepository);
    }
    @Bean
    public DeleteCustomerUseCase getDeleteCustomerUseCase(
            CustomerRepository customerRepository
    ) {
        return new DeleteCustomerUseCase(customerRepository);
    }
    @Bean
    public UpdateProductUseCase getUpdateProductUseCase(
            ProductsRepository productsRepository,
            RepositoryManager repositoryManager
    ) {
        return new UpdateProductUseCase(productsRepository, repositoryManager);
    }
    @Bean
    public UpdateCustomerUseCase getUpdateCustomerUseCase(
            CustomerRepository repository,
            RepositoryManager repositoryManager
    ) {
        return new UpdateCustomerUseCase(repository, repositoryManager);
    }

    @Bean
    public CreateSingleProductUseCase getCreateSingleProductUseCase(
            ProductsRepository productsRepository,
            RepositoryManager repositoryManager
    ) {
        return new CreateSingleProductUseCase(productsRepository, repositoryManager);
    }
    @Bean
    public GetProductByIdUseCase getGetProductByIdUseCase(
            ProductsRepository productsRepository
    ) {
        return new GetProductByIdUseCase(productsRepository);
    }
}
