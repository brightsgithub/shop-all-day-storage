package com.shopallday.storage.domain.usecases.brand;

import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.exceptions.crud.UpdateException;
import com.shopallday.storage.domain.models.Brand;
import com.shopallday.storage.domain.repository.products.BrandRepository;
import com.shopallday.storage.domain.usecases.UseCase;

import static com.shopallday.storage.domain.exceptions.BusinessErrorCodes.*;

public class UpdateBrandsUseCase implements UseCase<Brand, Brand> {

    private final BrandRepository brandRepository;

    public UpdateBrandsUseCase(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Brand execute(Brand brand) throws ReadException, UpdateException {
        try {
            final Long brandId = brand.getBrandId();
            if (brandId == null || !brandRepository.isExists(brandId)) {
                throw new ReadException("Cannot find Brand with id "+brandId, BRAND_NOT_FOUND);
            }

            return brandRepository.updateBrand(brand);
        } catch (ReadException e) {
            throw e;
        }
        catch (Exception e) {
            throw new UpdateException("Brand with id "+brand.getBrandId()+" could not be updated", BRAND_COULD_NOT_BE_UPDATED);
        }
    }
}
