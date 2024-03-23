package com.shopallday.storage.app.services.products;

import com.shopallday.storage.app.mappers.Mapper;
import com.shopallday.storage.app.models.CategoryDto;
import com.shopallday.storage.domain.exceptions.crud.CreateException;
import com.shopallday.storage.domain.exceptions.crud.DeleteException;
import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.exceptions.crud.UpdateException;
import com.shopallday.storage.domain.models.Category;
import com.shopallday.storage.domain.usecases.products.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final CreateSingleCategoryUseCase createSingleCategoryUseCase;
    private final GetCategoryUseCase getCategoryUseCase;
    private final GetCategoryByIdUseCase getCategoryByIdUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;
    @Qualifier("categoryMapper")
    private Mapper<Category, CategoryDto> categoryMapper;

    public CategoryServiceImpl(CreateCategoryUseCase createCategoryUseCase,
                               CreateSingleCategoryUseCase createSingleCategoryUseCase,
                               GetCategoryUseCase getCategoryUseCase,
                               GetCategoryByIdUseCase getCategoryByIdUseCase,
                               UpdateCategoryUseCase updateCategoryUseCase,
                               DeleteCategoryUseCase deleteCategoryUseCase,
                               @Qualifier("categoryMapper") Mapper<Category, CategoryDto> categoryMapper) {
        this.createCategoryUseCase = createCategoryUseCase;
        this.createSingleCategoryUseCase = createSingleCategoryUseCase;
        this.getCategoryUseCase = getCategoryUseCase;
        this.getCategoryByIdUseCase = getCategoryByIdUseCase;
        this.updateCategoryUseCase = updateCategoryUseCase;
        this.deleteCategoryUseCase = deleteCategoryUseCase;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) throws CreateException {
        final Category category = categoryMapper.mapFromDtoToDomain(categoryDto);
        final Category justCreatedCategory = createSingleCategoryUseCase.execute(category);
        return categoryMapper.mapFromDomainToDto(justCreatedCategory);
    }

    @Override
    public List<CategoryDto> getCategories() {
        return null;
    }

    @Override
    public CategoryDto getCategoryById(Long id) throws ReadException {
        return null;
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) throws ReadException, UpdateException {
        return null;
    }

    @Override
    public void deleteCategory(CategoryDto categoryDto) throws DeleteException {

    }

    @Override
    public CategoryDto partialUpdateCategory(Long id, Map<String, Object> fields) throws ReadException, UpdateException {
        return null;
    }
}
