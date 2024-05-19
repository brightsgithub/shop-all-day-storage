package com.shopallday.storage.domain.usecases.products;

import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.models.ProductType;
import com.shopallday.storage.domain.repository.products.ProductTypeRepository;
import com.shopallday.storage.domain.usecases.TestFactoryData;
import com.shopallday.storage.domain.usecases.producttype.GetProductTypesByCategoryIdUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetProductTypesByCategoryIdUseCaseTest {

    @Mock
    private ProductTypeRepository productTypeRepository;

    @InjectMocks
    private GetProductTypesByCategoryIdUseCase getProductTypesByCategoryIdUseCase;

    private List<ProductType> mockProductTypes;

    @BeforeEach
    void setUp() {
        mockProductTypes = TestFactoryData.createMockProductTypes(3);
    }

    @Test
    void execute_shouldReturnProductTypesForGivenCategoryId() throws ReadException {
        // Arrange
        Long categoryId = 1L;
        when(productTypeRepository.findProductTypesByCategoryId(categoryId)).thenReturn(mockProductTypes);

        // Act
        List<ProductType> result = getProductTypesByCategoryIdUseCase.execute(categoryId);

        // Assert
        assertEquals(mockProductTypes, result);
        verify(productTypeRepository, times(1)).findProductTypesByCategoryId(categoryId);
    }

    @Test
    void execute_should_return_empty_list_when_category_id_not_found() {
        // Arrange
        Long categoryId = 111111L;
        when(productTypeRepository.findProductTypesByCategoryId(categoryId)).thenReturn(new ArrayList<>());

        // Act
        List<ProductType> result = getProductTypesByCategoryIdUseCase.execute(categoryId);

        // Assert
        assertEquals(new ArrayList<>(), result);
        verify(productTypeRepository, times(1)).findProductTypesByCategoryId(categoryId);
    }
}

