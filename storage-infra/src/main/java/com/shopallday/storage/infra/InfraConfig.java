package com.shopallday.storage.infra;

import com.shopallday.storage.domain.initializers.StorageInitializer;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.repository.customer.CustomerRepository;
import com.shopallday.storage.domain.repository.customer.CustomerShippingAddRepository;
import com.shopallday.storage.domain.repository.delete.DeleteAllRepository;
import com.shopallday.storage.domain.repository.orders.CustomerOrderDetailRepository;
import com.shopallday.storage.domain.repository.orders.OrderLinesRepository;
import com.shopallday.storage.domain.repository.orders.OrderStatusTypeRepository;
import com.shopallday.storage.domain.repository.orders.OrdersRepository;
import com.shopallday.storage.domain.repository.products.*;
import com.shopallday.storage.infra.initializers.cache.CacheInitializer;
import com.shopallday.storage.infra.initializers.data.*;
import com.shopallday.storage.infra.repository.DeleteAllRepositoryImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The @Configuration annotation in Spring is used to indicate that a class declares one or more @Bean methods and can
 * be processed by the Spring IoC container to generate bean definitions and manage bean instances.
 */
@Configuration
//@ComponentScan(basePackages = {"com.shopallday.storage.infra"})
public class InfraConfig {

    @Bean(name = "getDataInitializer")
    public StorageInitializer getDataInitializer(
            @Qualifier("getProductData") DataHelper productData,
            @Qualifier("getCustomerData") DataHelper customerData,
            @Qualifier("getCategoryData") DataHelper categoryData,
            @Qualifier("getProductTypeData") DataHelper productTypeData,
            @Qualifier("getBrandData") DataHelper brandData,
            @Qualifier("getProductStockData") DataHelper productStockData,
            @Qualifier("getCustomerShippingAddressData") DataHelper customerShippingAddressData,
            @Qualifier("getOrdersData") DataHelper ordersData

    ) {
        return new DataInitializer(
                productData,
                customerData,
                categoryData,
                productTypeData,
                brandData,
                productStockData,
                customerShippingAddressData,
                ordersData);
    }
    @Bean(name = "getCacheInitializer")
    public StorageInitializer getCacheInitializer() {
        return new CacheInitializer();
    }

    @Bean(name = "getCustomerData")
    public DataHelper getCustomerData(CustomerRepository customerRepository) {
        return new CustomerData(customerRepository);
    }

    @Bean(name = "getProductData")
    public DataHelper getProductData(
            ProductsRepository productsRepository,
            ProductTypeRepository productTypeRepository,
            BrandRepository brandRepository,
            RepositoryManager repositoryManager
                                     ) {
        return new ProductData(productsRepository, productTypeRepository, brandRepository, repositoryManager);
    }

    @Bean(name = "getCategoryData")
    public DataHelper getCategoryData(CategoryRepository categoryRepository) {
        return new CategoryData(categoryRepository);
    }

    @Bean(name = "getProductTypeData")
    public DataHelper getProductTypeData(
            CategoryRepository categoryRepository,
            ProductTypeRepository productTypeRepository,
            RepositoryManager repositoryManager

    ) {
        return new ProductTypeData(categoryRepository, productTypeRepository, repositoryManager);
    }

    @Bean(name = "getBrandData")
    public DataHelper getBrandData(BrandRepository brandRepository) {
        return new BrandData(brandRepository);
    }



    @Bean(name = "getProductStockData")
    public DataHelper getProductStockData(
        ProductsRepository productsRepository,
        ProductStockRepository productStockRepository,
        RepositoryManager repositoryManager
    ) {
        return new ProductStockData(productsRepository, productStockRepository, repositoryManager);
    }
    @Bean(name = "getCustomerShippingAddressData")
    public DataHelper getCustomerShippingAddressData(
        CustomerRepository customerRepository,
        CustomerShippingAddRepository customerShippingAddRepository,
        RepositoryManager repositoryManager
    ) {
        return new CustomerShippingAddressData(
                customerRepository,
                customerShippingAddRepository,
                repositoryManager);
    }
    @Bean(name = "getOrdersData")
    public DataHelper getOrdersData(
        CustomerRepository customerRepository,
        CustomerOrderDetailRepository customerOrderDetailRepository,
        RepositoryManager repositoryManager,
        OrderStatusTypeRepository orderStatusTypeRepository,
        ProductStockRepository productStockRepository,
        ProductsRepository productsRepository,
        OrdersRepository ordersRepository,
        OrderLinesRepository orderLinesRepository
    ) {
        return new OrdersData(repositoryManager,
                customerOrderDetailRepository,
                customerRepository,
                orderStatusTypeRepository,
                productStockRepository,
                productsRepository,
                ordersRepository,
                orderLinesRepository);
    }

    @Bean(name = "deleteAllRepository")
    public DeleteAllRepository getDeleteAllRepository(
            CustomerRepository customerRepository,
            CustomerShippingAddRepository customerShippingAddRepository,
            CustomerOrderDetailRepository customerOrderDetailRepository,
            OrderLinesRepository orderLinesRepository,
            OrdersRepository ordersRepository,
            OrderStatusTypeRepository orderStatusTypeRepository,
            BrandRepository brandRepository,
            CategoryRepository categoryRepository,
            ProductsRepository productsRepository,
            ProductStockRepository productStockRepository,
            ProductTypeRepository productTypeRepository
    ) {
        return new DeleteAllRepositoryImpl(
                customerRepository,
                customerShippingAddRepository,
                customerOrderDetailRepository,
                orderLinesRepository,
                ordersRepository,
                orderStatusTypeRepository,
                brandRepository,
                categoryRepository,
                productsRepository,
                productStockRepository,
                productTypeRepository
        );
    }

    @Bean(name = "getDummyClass")
    public DummyClass getDummyClass() {
        return new DummyClass();
    }

}
