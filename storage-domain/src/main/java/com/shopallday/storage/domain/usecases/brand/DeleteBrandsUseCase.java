package com.shopallday.storage.domain.usecases.brand;

import com.shopallday.storage.domain.exceptions.BusinessErrorCodes;
import com.shopallday.storage.domain.exceptions.crud.DeleteException;
import com.shopallday.storage.domain.repository.products.BrandRepository;
import com.shopallday.storage.domain.usecases.UseCaseNoReturnVal;

public class DeleteBrandsUseCase implements UseCaseNoReturnVal<Long> {

    private final BrandRepository brandRepository;

    public DeleteBrandsUseCase(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public void execute(Long id) throws DeleteException {
        try{
            brandRepository.deleteBrandById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DeleteException("Brand with id "+ id+" could not be deleted", BusinessErrorCodes.BRAND_COULD_NOT_BE_DELETED);
        }
    }
}