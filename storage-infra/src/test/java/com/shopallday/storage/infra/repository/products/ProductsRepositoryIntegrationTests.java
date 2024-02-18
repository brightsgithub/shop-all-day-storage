package com.shopallday.storage.infra.repository.products;

import com.shopallday.storage.domain.models.Product;
import com.shopallday.storage.domain.repository.products.ProductsRepository;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.infra.repository.BaseIntegrationTests;
import com.shopallday.storage.infra.repository.TestFactoryData;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductsRepositoryIntegrationTests extends BaseIntegrationTests {

    private ProductsRepository productsRepository;
    private RepositoryManager repositoryManager;

    @Autowired
    public ProductsRepositoryIntegrationTests(ProductsRepository productsRepository, RepositoryManager repositoryManager) {
        this.productsRepository = productsRepository;
        this.repositoryManager = repositoryManager;
    }

    @Test
    @Transactional
    public void testThatProductsCanBeCreatedAndObtained() {
        List<Product> expected = TestFactoryData.createMockProducts(3);

        productsRepository.createProducts(expected, repositoryManager);

        List<Product> actual = productsRepository.findAllProducts();

        for (int i = 0; i < expected.size(); i++) {
            Product expectedProduct = expected.get(i);
            Product actuaProduct = actual.get(i);
            assertEquals(expectedProduct.getShortTitle(), actuaProduct.getShortTitle());
            assertEquals(expectedProduct.getBrand().getBrandName(), actuaProduct.getBrand().getBrandName());
            assertEquals(expectedProduct.getProductType().getProductTypeName(), actuaProduct.getProductType().getProductTypeName());
        }
    }
}
