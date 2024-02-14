package com.shopallday.storage.domain.usecases;

import com.shopallday.storage.domain.models.ProductType;
import com.shopallday.storage.domain.repository.CategoryRepository;
import com.shopallday.storage.domain.repository.ProductTypeRepository;
import com.shopallday.storage.domain.repository.RepositoryManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CreateProductTypeUseCaseTest {

    @Mock
    private ProductTypeRepository productTypeRepository;
    @Mock
    private RepositoryManager repositoryManager;

    @Mock
    private List<ProductType> productTypes;

    @InjectMocks
    private CreateProductTypeUseCase cut;

    @Test
    public void testThatCreateProductTypesHasBeenCalledOnRepo() {
        cut.execute(productTypes);

        verify(productTypeRepository).createProductTypes(productTypes, repositoryManager);
    }
}
