package com.shopallday.storage.domain.usecases.productstock;

import com.shopallday.storage.domain.exceptions.BusinessErrorCodes;
import com.shopallday.storage.domain.exceptions.crud.CreateException;
import com.shopallday.storage.domain.models.ProductStock;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.repository.products.ProductStockRepository;
import com.shopallday.storage.domain.usecases.UseCase;

public class CreateSingleProductStockUseCase implements UseCase<ProductStock, ProductStock> {
    private final ProductStockRepository productStockRepository;
    private final RepositoryManager repositoryManager;

    public CreateSingleProductStockUseCase(ProductStockRepository productStockRepository, RepositoryManager repositoryManager) {
        this.productStockRepository = productStockRepository;
        this.repositoryManager = repositoryManager;
    }

    @Override
    public ProductStock execute(ProductStock param) throws CreateException {
        try{
            return productStockRepository.createProductStock(param, repositoryManager);
        } catch (Exception e) {
          throw new CreateException("Could not create ProductStock", BusinessErrorCodes.PRODUCT_STOCK_COULD_NOT_BE_CREATED);
        }
    }
}
