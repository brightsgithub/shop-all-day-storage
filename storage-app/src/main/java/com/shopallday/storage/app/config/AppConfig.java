package com.shopallday.storage.app.config;

import com.shopallday.storage.app.mappers.CustomerMapper;
import com.shopallday.storage.app.mappers.Mapper;
import com.shopallday.storage.app.mappers.ProductMapper;
import com.shopallday.storage.app.models.CustomerDto;
import com.shopallday.storage.app.models.ProductDto;
import com.shopallday.storage.domain.models.Customer;
import com.shopallday.storage.domain.models.Product;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Mapper<Customer, CustomerDto> customerMapper() {
        return Mappers.getMapper(CustomerMapper.class);
    }

    @Bean
    public Mapper<Product, ProductDto> producrMapper() {
        return Mappers.getMapper(ProductMapper.class);
    }
}
