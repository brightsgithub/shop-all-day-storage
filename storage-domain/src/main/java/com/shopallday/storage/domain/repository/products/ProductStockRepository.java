package com.shopallday.storage.domain.repository.products;

import com.shopallday.storage.domain.models.ProductStock;
import com.shopallday.storage.domain.repository.RepositoryManager;

import java.util.List;

public interface ProductStockRepository {

    void createProductStock(ProductStock productStocks);
    void createProductStock(List<ProductStock> productStocks, RepositoryManager repositoryManager);

    List<ProductStock> findAllProductStocks();

    List<ProductStock> findProductStocksById(List<Long> ids);

    void updateProductStock(ProductStock productStock);

    void deleteProductStock(ProductStock productStock);
}
