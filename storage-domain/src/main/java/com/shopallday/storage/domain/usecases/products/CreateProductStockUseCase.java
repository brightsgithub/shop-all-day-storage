package com.shopallday.storage.domain.usecases.products;

import com.shopallday.storage.domain.models.ProductStock;
import com.shopallday.storage.domain.repository.ProductStockRepository;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.usecases.UseCaseNoReturnVal;

import java.util.List;

public class CreateProductStockUseCase implements UseCaseNoReturnVal<List<ProductStock>> {

    private final ProductStockRepository productStockRepository;
    private final RepositoryManager repositoryManager;

    public CreateProductStockUseCase(
            ProductStockRepository productStockRepository,
            RepositoryManager repositoryManager) {
        this.productStockRepository = productStockRepository;
        this.repositoryManager = repositoryManager;
    }


    @Override
    public void execute(List<ProductStock> params) {
        productStockRepository.createProductStock(params, repositoryManager);
    }
}
