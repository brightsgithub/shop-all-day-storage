package com.shopallday.storage.domain.repository.products;

import com.shopallday.storage.domain.models.ProductStock;
import com.shopallday.storage.domain.repository.RepositoryManager;

import java.util.List;

public interface ProductStockRepository {

    ProductStock createProductStock(ProductStock productStocks, RepositoryManager repositoryManager);
    List<ProductStock> createProductStock(List<ProductStock> productStocks, RepositoryManager repositoryManager);

    List<ProductStock> findAllProductStocks();

    List<ProductStock> findProductStocksById(List<Long> ids);

    ProductStock updateProductStock(ProductStock productStock, RepositoryManager repositoryManager);

    void deleteProductStock(Long id);
    boolean isExists(Long id);

    void deleteAll();
}
