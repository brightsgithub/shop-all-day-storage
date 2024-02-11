package com.shopallday.storage.domain.usecases;

import com.shopallday.storage.domain.models.ProductStock;
import com.shopallday.storage.domain.repository.ProductStockRepository;

import java.util.List;

public class CreateProductStockUseCase implements UseCaseNoReturnVal<List<ProductStock>>{

    private final ProductStockRepository productStockRepository;

    public CreateProductStockUseCase(ProductStockRepository productStockRepository) {
        this.productStockRepository = productStockRepository;
    }


    @Override
    public void execute(List<ProductStock> params) {
        productStockRepository.createProductStock(params);
    }
}
