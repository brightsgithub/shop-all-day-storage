package com.shopallday.storage.domain.usecases.products;

import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.models.Brand;
import com.shopallday.storage.domain.repository.products.BrandRepository;
import com.shopallday.storage.domain.usecases.UseCase;

import java.util.List;

import static com.shopallday.storage.domain.exceptions.BusinessErrorCodes.BRAND_NOT_FOUND;

public class GetBrandByIdUseCase implements UseCase<Brand, Long> {

    private final BrandRepository brandRepository;

    public GetBrandByIdUseCase(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Brand execute(Long param) throws ReadException {
        List<Brand> list = brandRepository.findBrandById(param);
        if (list.isEmpty()) {
            throw new ReadException("Brand with id "+param+" could not be found.", BRAND_NOT_FOUND);
        }
        return list.get(0);
    }
}
