package com.shopallday.storage.domain.usecases;

import com.shopallday.storage.domain.models.ProductType;
import com.shopallday.storage.domain.repository.ProductTypeRepository;

import java.util.List;

public class GetAllProductTypesUseCase {

    private final ProductTypeRepository productTypeRepository;

    public GetAllProductTypesUseCase(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    public List<ProductType> execute() {
        return productTypeRepository.findAllProductTypes();
    }
}
