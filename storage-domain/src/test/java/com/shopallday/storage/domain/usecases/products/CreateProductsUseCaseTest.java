package com.shopallday.storage.domain.usecases.products;

import com.shopallday.storage.domain.models.Product;
import com.shopallday.storage.domain.repository.ProductsRepository;
import com.shopallday.storage.domain.repository.RepositoryManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CreateProductsUseCaseTest {

    @Mock
    private ProductsRepository productsRepository;
    @Mock
    private RepositoryManager repositoryManager;

    @Mock
    private List<Product> productList;

    @InjectMocks
    private CreateProductsUseCase underTest;

    @Test
    public void testThatCreateProductsIsCalledOnRepo() {
        underTest.execute(productList);

        verify(productsRepository).createProducts(productList, repositoryManager);
    }
}
