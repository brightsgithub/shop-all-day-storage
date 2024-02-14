package com.shopallday.storage.domain.repository;

import com.shopallday.storage.domain.models.Product;

import java.util.List;

public interface ProductsRepository {

    void createProduct(Product product);

    void createProducts(List<Product> products, RepositoryManager manager);

    List<Product> findProductsByIds(List<Long> ids);

    List<Product> findAllProducts();

    void updateProduct(Product product);

    void deleteProduct(Product product);
}
