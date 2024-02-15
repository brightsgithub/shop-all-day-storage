package com.shopallday.storage.domain.repository;

import com.shopallday.storage.domain.models.ProductStock;

import java.util.List;

public interface ProductStockRepository {

    void createProductStock(ProductStock productStocks);
    void createProductStock(List<ProductStock> productStocks, RepositoryManager repositoryManager);

    List<ProductStock> findAllProductStocks();

    List<ProductStock> findProductStocksById(List<Long> ids);

    void updateProductStock(ProductStock productStock);

    void deleteProductStock(ProductStock productStock);
}
