package com.shopallday.storage.domain.usecases;

import com.shopallday.storage.domain.models.Brand;
import com.shopallday.storage.domain.repository.BrandRepository;

import java.util.List;

public class CreateBrandsUseCase implements UseCaseNoReturnVal<List<Brand>>{

    private final BrandRepository brandRepository;

    public CreateBrandsUseCase(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public void execute(List<Brand> param) {
        brandRepository.createBrands(param);
    }
}
