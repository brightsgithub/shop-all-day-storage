package com.shopallday.storage.infra.repository;

import com.shopallday.storage.infra.InfraConfig;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ContextConfiguration(classes = {InfraConfig.class, JPAConfigForTest.class})
@EnableJpaRepositories
@EnableAutoConfiguration
@ExtendWith(SpringExtension.class)
public class BaseIntegrationTests {
}
