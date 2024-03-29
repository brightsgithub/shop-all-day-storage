package com.shopallday.storage.domain.usecases.productstock;

import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.exceptions.crud.UpdateException;
import com.shopallday.storage.domain.models.ProductStock;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.repository.products.ProductStockRepository;
import com.shopallday.storage.domain.usecases.UseCase;

import static com.shopallday.storage.domain.exceptions.BusinessErrorCodes.*;

public class UpdateProductStockUseCase implements UseCase<ProductStock, ProductStock> {
    private final ProductStockRepository productStockRepository;
    private final RepositoryManager repositoryManager;

    public UpdateProductStockUseCase(ProductStockRepository productStockRepository, RepositoryManager repositoryManager) {
        this.productStockRepository = productStockRepository;
        this.repositoryManager = repositoryManager;
    }

    @Override
    public ProductStock execute(ProductStock productStock) throws ReadException, UpdateException {
        try {
            checkIfItemExists(productStock);
            return productStockRepository.updateProductStock(productStock, repositoryManager);
        } catch (ReadException e) {
            throw e;
        }
        catch (Exception e) {
            throw new UpdateException("ProductStock with id "+productStock.getProductStockId()+" could not be updated", PRODUCT_STOCK_NOT_FOUND);
        }
    }

    private void checkIfItemExists(ProductStock productStock) throws ReadException {
        final Long id = productStock.getProductStockId();
        if (id == null || !productStockRepository.isExists(id)) {
            throw new ReadException("Cannot find ProductStock with id "+id, PRODUCT_STOCK_NOT_FOUND);
        }
    }
}
