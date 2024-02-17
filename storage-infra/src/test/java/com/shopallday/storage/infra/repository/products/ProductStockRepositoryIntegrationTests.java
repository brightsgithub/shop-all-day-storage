package com.shopallday.storage.infra.repository.products;

import com.shopallday.storage.domain.models.ProductStock;
import com.shopallday.storage.domain.repository.ProductStockRepository;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.infra.repository.BaseIntegrationTests;
import com.shopallday.storage.infra.repository.TestFactoryData;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductStockRepositoryIntegrationTests extends BaseIntegrationTests {

    private ProductStockRepository productStockRepository;
    private RepositoryManager repositoryManager;

    @Autowired
    public ProductStockRepositoryIntegrationTests(ProductStockRepository productStockRepository, RepositoryManager repositoryManager) {
        this.productStockRepository = productStockRepository;
        this.repositoryManager = repositoryManager;
    }


    @Test
    @Transactional
    public void testThatProductStocksCanBeCreatedAndObtained() {
        List<ProductStock> expectedProductStocks = TestFactoryData.createMockProductStock(3);

        productStockRepository.createProductStock(expectedProductStocks, repositoryManager);

        List<ProductStock> actualProductStocks = productStockRepository.findAllProductStocks();

        for (int i = 0; i < expectedProductStocks.size(); i++) {
            ProductStock expectedProductStock = expectedProductStocks.get(i);
            ProductStock actualProductStock = actualProductStocks.get(i);

            assertEquals(expectedProductStock.getQuantity(), actualProductStock.getQuantity());
            assertEquals(expectedProductStock.getSize(), actualProductStock.getSize());
            assertEquals(expectedProductStock.getColor(), actualProductStock.getColor());
            assertEquals(expectedProductStock.getProduct().getShortTitle(), actualProductStock.getProduct().getShortTitle());
            assertEquals(expectedProductStock.getProduct().getProductType().getProductTypeName(), actualProductStock.getProduct().getProductType().getProductTypeName());
            assertEquals(expectedProductStock.getProduct().getProductType().getCategory().getCategoryName(), actualProductStock.getProduct().getProductType().getCategory().getCategoryName());

        }
    }
}
