package com.shopallday.storage.app.config;

import com.shopallday.storage.domain.repository.CategoryRepository;
import com.shopallday.storage.domain.repository.CustomerRepository;
import com.shopallday.storage.domain.repository.SampleRepo;
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
}
