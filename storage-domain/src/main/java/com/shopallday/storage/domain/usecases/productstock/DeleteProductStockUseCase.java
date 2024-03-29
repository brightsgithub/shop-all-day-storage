package com.shopallday.storage.domain.usecases.productstock;

import com.shopallday.storage.domain.exceptions.BusinessErrorCodes;
import com.shopallday.storage.domain.exceptions.crud.DeleteException;
import com.shopallday.storage.domain.repository.products.ProductStockRepository;
import com.shopallday.storage.domain.usecases.UseCaseNoReturnVal;

public class DeleteProductStockUseCase implements UseCaseNoReturnVal<Long> {
    private final ProductStockRepository productStockRepository;

    public DeleteProductStockUseCase(ProductStockRepository productStockRepository) {
        this.productStockRepository = productStockRepository;
    }

    @Override
    public void execute(Long id) throws DeleteException {
        try {
            productStockRepository.deleteProductStock(id);
        } catch (Exception e) {
            throw new DeleteException("ProductStock with id "+ id+" could not be deleted", BusinessErrorCodes.PRODUCT_STOCK_COULD_NOT_BE_DELETED);
        }
    }
}
