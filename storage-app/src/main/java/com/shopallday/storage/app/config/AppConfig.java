package com.shopallday.storage.app.config;

import com.shopallday.storage.app.mappers.CustomerMapper;
import com.shopallday.storage.app.mappers.Mapper;
import com.shopallday.storage.app.models.CustomerDto;
import com.shopallday.storage.domain.models.Customer;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Mapper<Customer, CustomerDto> customerMapper() {
        return Mappers.getMapper(CustomerMapper.class);
    }
}
