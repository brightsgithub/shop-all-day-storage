package com.shopallday.storage.domain.usecases.products;

import com.shopallday.storage.domain.exceptions.BusinessErrorCodes;
import com.shopallday.storage.domain.exceptions.crud.DeleteException;
import com.shopallday.storage.domain.repository.products.CategoryRepository;
import com.shopallday.storage.domain.usecases.UseCaseNoReturnVal;

public class DeleteCategoryUseCase implements UseCaseNoReturnVal<Long> {

    private final CategoryRepository categoryRepository;

    public DeleteCategoryUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void execute(Long id) throws Exception {
        try{
            categoryRepository.deleteCategoryById(id);
        } catch (Exception e) {
            throw new DeleteException("Category with id "+ id+" could not be deleted", BusinessErrorCodes.CATEGORY_COULD_NOT_BE_DELETED);
        }
    }
}
