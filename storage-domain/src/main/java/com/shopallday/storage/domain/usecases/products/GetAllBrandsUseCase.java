package com.shopallday.storage.domain.usecases.products;

import com.shopallday.storage.domain.models.Brand;
import com.shopallday.storage.domain.repository.products.BrandRepository;
import com.shopallday.storage.domain.usecases.UseCaseNoParam;

import java.util.List;

public class GetAllBrandsUseCase implements UseCaseNoParam<List<Brand>> {

    private final BrandRepository brandRepository;

    public GetAllBrandsUseCase(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<Brand> execute() {
        return brandRepository.findAllBrands();
    }
}
