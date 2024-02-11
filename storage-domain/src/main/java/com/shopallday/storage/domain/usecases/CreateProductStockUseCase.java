package com.shopallday.storage.domain.usecases;

import com.shopallday.storage.domain.models.ProductStock;
import com.shopallday.storage.domain.repository.ProductStockRepository;

import java.util.List;

public class CreateProductStockUseCase implements UseCase<Void, List<ProductStock>>{

    private final ProductStockRepository productStockRepository;

    public CreateProductStockUseCase(ProductStockRepository productStockRepository) {
        this.productStockRepository = productStockRepository;
    }


    @Override
    public Void execute(List<ProductStock> params) {
        productStockRepository.createProductStock(params);
        return null;
    }
}
