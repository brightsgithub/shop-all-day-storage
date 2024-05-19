package com.shopallday.storage.app;

import com.shopallday.storage.domain.usecases.DeleteAllUseCase;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test-storage-app-application.properties")
public class BaseControllerIntegrationTests {
    private final DeleteAllUseCase deleteAllUseCase;

    public BaseControllerIntegrationTests(DeleteAllUseCase deleteAllUseCase) {
        this.deleteAllUseCase = deleteAllUseCase;
    }

    // wipe all data between tests
    @BeforeEach
    @Transactional
    public void before() throws Exception {
        // need to maybe consider constraints and cascading
        deleteAllUseCase.execute();
    }
}
