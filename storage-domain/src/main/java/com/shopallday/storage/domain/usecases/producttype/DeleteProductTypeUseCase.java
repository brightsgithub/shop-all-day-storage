package com.shopallday.storage.domain.usecases.producttype;

import com.shopallday.storage.domain.exceptions.BusinessErrorCodes;
import com.shopallday.storage.domain.exceptions.crud.DeleteException;
import com.shopallday.storage.domain.repository.products.ProductTypeRepository;
import com.shopallday.storage.domain.usecases.UseCaseNoReturnVal;

public class DeleteProductTypeUseCase implements UseCaseNoReturnVal<Long> {

    private final ProductTypeRepository repository;

    public DeleteProductTypeUseCase(ProductTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Long id) throws DeleteException {
        try{
            repository.deleteProductTypeById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DeleteException("DeleteProductType with id "+ id+" could not be deleted", BusinessErrorCodes.PRODUCT_TYPE_COULD_NOT_BE_DELETED);
        }
    }
}
