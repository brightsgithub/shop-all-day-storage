package com.shopallday.storage.domain.usecases.products;

import com.shopallday.storage.domain.models.Category;
import com.shopallday.storage.domain.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CreateCategoryUseCaseTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private List<Category> categoryList;
    @InjectMocks
    private CreateCategoryUseCase underTest;

    @Test
    public void testThatCreateCategoriesIsCalledOnRepo() {
        underTest.execute(categoryList);
        verify(categoryRepository).createCategories(categoryList);
    }
}
