package com.shopallday.storage.domain.usecases.products;

import com.shopallday.storage.domain.models.Product;
import com.shopallday.storage.domain.repository.products.ProductsRepository;
import com.shopallday.storage.domain.usecases.TestFactoryData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAllProductsUseCaseTest {

    @Mock
    private ProductsRepository productsRepository;

    @InjectMocks
    private GetAllProductsUseCase getAllProductsUseCase;

    @Test
    public void testExecute() {
        // Arrange
        List<Product> expectedProducts = TestFactoryData.createMockProducts(2);

        // Mocking behavior of the productsRepository
        when(productsRepository.findAllProducts()).thenReturn(expectedProducts);

        // Act
        List<Product> actualProducts = getAllProductsUseCase.execute();

        // Assert
        assertEquals(expectedProducts.size(), actualProducts.size());
        for (int i = 0; i < expectedProducts.size(); i++) {
            Product expectedProduct = expectedProducts.get(i);
            Product actualProduct = actualProducts.get(i);
            assertEquals(expectedProduct.getProductId(), actualProduct.getProductId());
            assertEquals(expectedProduct.getShortTitle(), actualProduct.getShortTitle());
            assertEquals(expectedProduct.getLongTitle(), actualProduct.getLongTitle());
            assertEquals(expectedProduct.getShortDescription(), actualProduct.getShortDescription());
            assertEquals(expectedProduct.getLongDescription(), actualProduct.getLongDescription());
        }
    }
}
