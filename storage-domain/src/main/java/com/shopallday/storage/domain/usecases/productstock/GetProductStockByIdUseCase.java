package com.shopallday.storage.domain.usecases.productstock;

import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.models.ProductStock;
import com.shopallday.storage.domain.repository.products.ProductStockRepository;
import com.shopallday.storage.domain.usecases.UseCase;

import java.util.List;

import static com.shopallday.storage.domain.exceptions.BusinessErrorCodes.PRODUCT_TYPE_NOT_FOUND;

public class GetProductStockByIdUseCase implements UseCase<ProductStock, Long> {
    private final ProductStockRepository productStockRepository;

    public GetProductStockByIdUseCase(ProductStockRepository productStockRepository) {
        this.productStockRepository = productStockRepository;
    }

    @Override
    public ProductStock execute(Long param) throws ReadException {
        List<ProductStock> list = productStockRepository.findProductStocksById(List.of(param));
        if (list.isEmpty()) {
            throw new ReadException("ProductStock with id "+param+" could not be found.", PRODUCT_TYPE_NOT_FOUND);
        }
        return list.get(0);
    }
}
