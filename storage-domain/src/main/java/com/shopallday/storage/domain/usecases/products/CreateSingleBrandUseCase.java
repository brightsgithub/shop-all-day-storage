package com.shopallday.storage.domain.usecases.products;

import com.shopallday.storage.domain.exceptions.BusinessErrorCodes;
import com.shopallday.storage.domain.exceptions.crud.CreateException;
import com.shopallday.storage.domain.models.Brand;
import com.shopallday.storage.domain.repository.products.BrandRepository;
import com.shopallday.storage.domain.usecases.UseCase;

public class CreateSingleBrandUseCase implements UseCase<Brand, Brand> {

    private final BrandRepository brandRepository;

    public CreateSingleBrandUseCase(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Brand execute(Brand param) throws CreateException {
        try {
            return brandRepository.createBrand(param);
        } catch (Exception e) {
            throw new CreateException("Could not create Brand", BusinessErrorCodes.BRAND_COULD_NOT_BE_CREATED);
        }
    }
}