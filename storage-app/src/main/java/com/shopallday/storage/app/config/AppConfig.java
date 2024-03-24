package com.shopallday.storage.app.config;

import com.shopallday.storage.app.mappers.*;
import com.shopallday.storage.app.models.BrandDto;
import com.shopallday.storage.app.models.CategoryDto;
import com.shopallday.storage.app.models.CustomerDto;
import com.shopallday.storage.app.models.ProductDto;
import com.shopallday.storage.domain.models.Brand;
import com.shopallday.storage.domain.models.Category;
import com.shopallday.storage.domain.models.Customer;
import com.shopallday.storage.domain.models.Product;
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
}
