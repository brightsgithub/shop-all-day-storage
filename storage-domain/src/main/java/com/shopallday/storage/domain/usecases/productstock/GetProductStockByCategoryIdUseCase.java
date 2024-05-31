package com.shopallday.storage.domain.usecases.productstock;

import com.shopallday.storage.domain.models.ProductStock;
import com.shopallday.storage.domain.repository.products.ProductStockRepository;
import com.shopallday.storage.domain.usecases.UseCase;

import java.util.List;

public class GetProductStockByCategoryIdUseCase implements UseCase<List<ProductStock>, Long> {
    private final ProductStockRepository repository;
    public GetProductStockByCategoryIdUseCase(ProductStockRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ProductStock> execute(Long categoryId) {
        return repository.findProductStockByCategoryId(categoryId);
    }
}