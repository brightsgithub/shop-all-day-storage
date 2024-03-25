package com.shopallday.storage.domain.usecases.products;

import com.shopallday.storage.domain.models.ProductType;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.repository.products.ProductTypeRepository;
import com.shopallday.storage.domain.usecases.TestFactoryData;
import com.shopallday.storage.domain.usecases.producttype.GetAllProductTypesUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAllProductTypesUseCaseTest {

    @Mock
    private ProductTypeRepository productTypeRepository;

    @Mock
    private RepositoryManager repositoryManager;
    @InjectMocks
    private GetAllProductTypesUseCase getAllProductTypesUseCase;

    @Test
    public void testExecute() {
        // Arrange
        List<ProductType> expectedProductTypes = TestFactoryData.createMockProductTypes(2);

        // Mocking behavior of the productTypeRepository
        when(productTypeRepository.findAllProductTypes()).thenReturn(expectedProductTypes);

        // Act
        List<ProductType> actualProductTypes = getAllProductTypesUseCase.execute();

        // Assert
        assertEquals(expectedProductTypes.size(), actualProductTypes.size());
        for (int i = 0; i < expectedProductTypes.size(); i++) {
            ProductType expectedProductType = expectedProductTypes.get(i);
            ProductType actualProductType = actualProductTypes.get(i);
            assertEquals(expectedProductType.getProductTypeId(), actualProductType.getProductTypeId());
            assertEquals(expectedProductType.getCategory().getCategoryId(), actualProductType.getCategory().getCategoryId());
            assertEquals(expectedProductType.getCategory().getCategoryName(), actualProductType.getCategory().getCategoryName());
            assertEquals(expectedProductType.getProductTypeName(), actualProductType.getProductTypeName());
        }
    }
}
