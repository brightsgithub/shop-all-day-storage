package com.shopallday.storage.domain.usecases.producttype;

import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.models.ProductType;
import com.shopallday.storage.domain.repository.products.ProductTypeRepository;
import com.shopallday.storage.domain.usecases.UseCase;

public class GetProductTypeByIdUseCase implements UseCase<ProductType, Long> {
    private final ProductTypeRepository repository;
    public GetProductTypeByIdUseCase(ProductTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductType execute(Long param) throws ReadException {
        return repository.findProductTypeById(param);
    }
}
