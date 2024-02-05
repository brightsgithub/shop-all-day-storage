package com.shopallday.storage.app.config;

import com.shopallday.storage.domain.repository.CustomerRepository;
import com.shopallday.storage.domain.repository.SampleRepo;
import com.shopallday.storage.domain.usecases.CreateCustomersUseCase;
import com.shopallday.storage.domain.usecases.GetAllCustomersUseCase;
import com.shopallday.storage.domain.usecases.SampleUseCase;
import com.shopallday.storage.infra.repository.SampleRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfig {

//    @Autowired
//    private CustomerRepository customerRepository;

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
}
