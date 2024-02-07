package com.shopallday.storage.domain.usecases;

import com.shopallday.storage.domain.models.ProductType;
import com.shopallday.storage.domain.repository.ProductTypeRepository;

import java.util.List;

public class CreateProductTypeUseCase {

    private final ProductTypeRepository productTypeRepository;

    public CreateProductTypeUseCase(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    public void execute(List<ProductType> productTypes) {
        productTypeRepository.createProductTypes(productTypes);
    }
}
