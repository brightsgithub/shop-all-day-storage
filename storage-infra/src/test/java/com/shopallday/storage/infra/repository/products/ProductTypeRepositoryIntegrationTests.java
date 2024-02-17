package com.shopallday.storage.infra.repository.products;

import com.shopallday.storage.domain.models.ProductType;
import com.shopallday.storage.domain.repository.ProductTypeRepository;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.infra.repository.BaseIntegrationTests;
import com.shopallday.storage.infra.repository.TestFactoryData;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ProductTypeRepositoryIntegrationTests extends BaseIntegrationTests {

    private ProductTypeRepository productTypeRepository;
    private RepositoryManager repositoryManager;

    @Autowired
    public ProductTypeRepositoryIntegrationTests(
            ProductTypeRepository productTypeRepository,
            RepositoryManager repositoryManager) {
        this.productTypeRepository = productTypeRepository;
        this.repositoryManager = repositoryManager;
    }

    @Test
    @Transactional
    public void testThatProductTypeCanBeCreatedAndObtained() {

        List<ProductType> expectedProductTypes = TestFactoryData.createMockProductTypes(3);

        productTypeRepository.createProductTypes(expectedProductTypes, repositoryManager);

        List<ProductType> actualProductTypes = productTypeRepository.findAllProductTypes();

        for (int i = 0; i < expectedProductTypes.size(); i++) {
            ProductType expectedProductType = expectedProductTypes.get(i);
            ProductType actualProductType = actualProductTypes.get(i);
            assertEquals(expectedProductType.getProductTypeName(), actualProductType.getProductTypeName());
            assertEquals(expectedProductType.getCategory().getCategoryName(), actualProductType.getCategory().getCategoryName());
        }
    }
}
