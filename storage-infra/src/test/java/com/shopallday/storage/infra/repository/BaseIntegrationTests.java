package com.shopallday.storage.infra.repository;

import com.shopallday.storage.infra.InfraConfig;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Transactional rollback: Annotate your test methods with @Transactional and ensure that transactions are rolled back
 * after the test completes. Spring TestContext Framework provides built-in support for transactional rollback for test
 * methods annotated with @Transactional.
 */
@SpringBootTest
@ContextConfiguration(classes = {InfraConfig.class, JPAConfigForTest.class})
@EnableJpaRepositories
@EnableAutoConfiguration
@ExtendWith(SpringExtension.class)
public class BaseIntegrationTests {
}
