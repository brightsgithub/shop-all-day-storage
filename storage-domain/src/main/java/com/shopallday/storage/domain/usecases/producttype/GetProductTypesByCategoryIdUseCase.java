package com.shopallday.storage.domain.usecases.producttype;

import com.shopallday.storage.domain.models.ProductType;
import com.shopallday.storage.domain.repository.products.ProductTypeRepository;
import com.shopallday.storage.domain.usecases.UseCase;

import java.util.List;

public class GetProductTypesByCategoryIdUseCase implements UseCase<List<ProductType>, Long> {
    private final ProductTypeRepository repository;
    public GetProductTypesByCategoryIdUseCase(ProductTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ProductType> execute(Long categoryId) {
        return repository.findProductTypesByCategoryId(categoryId);
    }
}
