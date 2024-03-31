package com.shopallday.storage.app.config;

import com.shopallday.storage.app.mappers.*;
import com.shopallday.storage.app.models.*;
import com.shopallday.storage.domain.models.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Qualifier("productMapper")
    @Bean
    public Mapper<Product, ProductDto> producrMapper() {
        return Mappers.getMapper(ProductMapper.class);
    }
    @Qualifier("customerMapper")
    @Bean
    public Mapper<Customer, CustomerDto> customerMapper() {
        return Mappers.getMapper(CustomerMapper.class);
    }
    @Qualifier("categoryMapper")
    @Bean
    public Mapper<Category, CategoryDto> categoryMapper() {
        return Mappers.getMapper(CategoryMapper.class);
    }
    @Qualifier("brandMapper")
    @Bean
    public Mapper<Brand, BrandDto> brandMapper() {
        return Mappers.getMapper(BrandMapper.class);
    }
    @Qualifier("productTypeMapper")
    @Bean
    public Mapper<ProductType, ProductTypeDto> productTypeMapper() {
        return Mappers.getMapper(ProductTypeMapper.class);
    }
    @Qualifier("productStockMapper")
    @Bean
    public Mapper<ProductStock, ProductStockDto> productStockMapper() {
        return Mappers.getMapper(ProductStockMapperApp.class);
    }
    @Qualifier("orderStatusTypeMapper")
    @Bean
    public Mapper<OrderStatusType, OrderStatusTypeDto> orderStatusTypeMapper() {
        return Mappers.getMapper(OrderStatusTypeMapperApp.class);
    }
    @Qualifier("orderMapper")
    @Bean
    public Mapper<Order, OrderDto> orderMapper() {
        return Mappers.getMapper(OrderMapperApp.class);
    }
    @Qualifier("orderLinesMapper")
    @Bean
    public Mapper<OrderLine, OrderLineDto> orderLinesMapper() {
        return Mappers.getMapper(OrderLinesMapperApp.class);
    }
    @Qualifier("customerOrderDetailMapper")
    @Bean
    public Mapper<CustomerOrderDetail, CustomerOrderDetailDto> customerOrderDetailMapper() {
        return Mappers.getMapper(CustomerOrderDetailMapperApp.class);
    }
}
