package com.shopallday.storage.domain.usecases;

import com.shopallday.storage.domain.models.Brand;
import com.shopallday.storage.domain.repository.BrandRepository;

import java.util.List;

public class CreateBrandsUseCase implements UseCase<Void, List<Brand>>{

    private final BrandRepository brandRepository;

    public CreateBrandsUseCase(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public Void execute(List<Brand> brandList) {
        brandRepository.createBrands(brandList);
        return null;
    }
}
