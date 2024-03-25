package com.shopallday.storage.domain.usecases.products;

import com.shopallday.storage.domain.models.ProductStock;
import com.shopallday.storage.domain.repository.products.ProductStockRepository;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.usecases.productstock.CreateProductStockUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CreateProductStockUseCaseTest {

    @Mock
    private List<ProductStock> productStockList;

    @Mock
    private ProductStockRepository productStockRepository;
    @Mock
    private RepositoryManager repositoryManager;

    @InjectMocks
    private CreateProductStockUseCase underTest;

    @Test
    public void testThatCreateProductStockWasCalledOnRepo() {
        underTest.execute(productStockList);

        verify(productStockRepository).createProductStock(productStockList, repositoryManager);
    }

}
