package com.shopallday.storage.domain.usecases.producttype;

import com.shopallday.storage.domain.models.ProductType;
import com.shopallday.storage.domain.repository.products.ProductTypeRepository;
import com.shopallday.storage.domain.usecases.UseCaseNoParam;

import java.util.List;

public class GetAllProductTypesUseCase implements UseCaseNoParam<List<ProductType>> {

    private final ProductTypeRepository productTypeRepository;

    public GetAllProductTypesUseCase(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    public List<ProductType> execute() {
        return productTypeRepository.findAllProductTypes();
    }
}
