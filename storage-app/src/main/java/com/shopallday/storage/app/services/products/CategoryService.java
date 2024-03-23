package com.shopallday.storage.app.services.products;

import com.shopallday.storage.app.models.CategoryDto;
import com.shopallday.storage.domain.exceptions.crud.CreateException;
import com.shopallday.storage.domain.exceptions.crud.DeleteException;
import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.exceptions.crud.UpdateException;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto) throws CreateException;
    List<CategoryDto> getCategories();
    CategoryDto getCategoryById(Long id) throws ReadException;
    CategoryDto updateCategory(CategoryDto categoryDto) throws ReadException, UpdateException;
    void deleteCategoryById(Long id) throws DeleteException;
    CategoryDto partialUpdateCategory(final Long id, final Map<String, Object> fields) throws ReadException, UpdateException;
}
