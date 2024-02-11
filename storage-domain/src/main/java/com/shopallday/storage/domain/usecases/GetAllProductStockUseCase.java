package com.shopallday.storage.domain.usecases;

import com.shopallday.storage.domain.models.ProductStock;
import com.shopallday.storage.domain.repository.ProductStockRepository;

import java.util.List;

public class GetAllProductStockUseCase implements UseCaseNoParam<List<ProductStock>> {

    private final ProductStockRepository productStockRepository;

    public GetAllProductStockUseCase(ProductStockRepository productStockRepository) {
        this.productStockRepository = productStockRepository;
    }

    @Override
    public List<ProductStock> execute() {
        return productStockRepository.findAllProductStocks();
    }
}
