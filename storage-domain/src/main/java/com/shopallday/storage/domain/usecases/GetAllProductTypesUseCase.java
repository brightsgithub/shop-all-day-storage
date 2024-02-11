package com.shopallday.storage.domain.usecases;

import com.shopallday.storage.domain.models.ProductType;
import com.shopallday.storage.domain.repository.ProductTypeRepository;

import java.util.List;

public class GetAllProductTypesUseCase implements UseCaseNoParam<List<ProductType>>{

    private final ProductTypeRepository productTypeRepository;

    public GetAllProductTypesUseCase(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    public List<ProductType> execute() {
        return productTypeRepository.findAllProductTypes();
    }
}
