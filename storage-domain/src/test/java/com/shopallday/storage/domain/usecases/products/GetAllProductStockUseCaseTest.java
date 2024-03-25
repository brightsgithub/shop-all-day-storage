package com.shopallday.storage.domain.usecases.products;

import com.shopallday.storage.domain.models.ProductStock;
import com.shopallday.storage.domain.repository.products.ProductStockRepository;
import com.shopallday.storage.domain.usecases.TestFactoryData;
import com.shopallday.storage.domain.usecases.productstock.GetAllProductStockUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAllProductStockUseCaseTest {

    @Mock
    private ProductStockRepository productStockRepository;

    @InjectMocks
    private GetAllProductStockUseCase getAllProductStockUseCase;

    @Test
    public void testExecute() {
        // Arrange
        List<ProductStock> expectedProductStocks = TestFactoryData.createMockProductStock(2);

        // Mocking behavior of the productStockRepository
        when(productStockRepository.findAllProductStocks()).thenReturn(expectedProductStocks);

        // Act
        List<ProductStock> actualProductStocks = getAllProductStockUseCase.execute();

        // Assert
        assertEquals(expectedProductStocks.size(), actualProductStocks.size());
        for (int i = 0; i < expectedProductStocks.size(); i++) {
            ProductStock expectedProductStock = expectedProductStocks.get(i);
            ProductStock actualProductStock = actualProductStocks.get(i);
            assertEquals(expectedProductStock.getProductStockId(), actualProductStock.getProductStockId());
            assertEquals(expectedProductStock.getProduct().getProductId(), actualProductStock.getProduct().getProductId());
            assertEquals(expectedProductStock.getProduct().getBrand().getBrandId(), actualProductStock.getProduct().getBrand().getBrandId());
            assertEquals(expectedProductStock.getProduct().getShortTitle(), actualProductStock.getProduct().getShortTitle());
            assertEquals(expectedProductStock.getProduct().getLongTitle(), actualProductStock.getProduct().getLongTitle());
            assertEquals(expectedProductStock.getProduct().getShortDescription(), actualProductStock.getProduct().getShortDescription());
            assertEquals(expectedProductStock.getProduct().getLongDescription(), actualProductStock.getProduct().getLongDescription());
            assertEquals(expectedProductStock.getQuantity(), actualProductStock.getQuantity());
            assertEquals(expectedProductStock.getSize(), actualProductStock.getSize());
            assertEquals(expectedProductStock.getColor(), actualProductStock.getColor());
            assertEquals(expectedProductStock.getPrice(), actualProductStock.getPrice());
        }
    }
}
