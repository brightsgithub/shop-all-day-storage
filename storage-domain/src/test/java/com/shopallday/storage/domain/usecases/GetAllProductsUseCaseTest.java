package com.shopallday.storage.domain.usecases;

import com.shopallday.storage.domain.models.Product;
import com.shopallday.storage.domain.repository.ProductsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
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
        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(new Product(1L, null, null, "ShortTitle1", "LongTitle1", "ShortDescription1", "LongDescription1"));
        expectedProducts.add(new Product(2L, null, null, "ShortTitle2", "LongTitle2", "ShortDescription2", "LongDescription2"));

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
