package com.shopallday.storage.domain.repository.products;

import com.shopallday.storage.domain.models.Product;
import com.shopallday.storage.domain.repository.RepositoryManager;

import java.util.List;

public interface ProductsRepository {

    void createProduct(Product product);

    void createProducts(List<Product> products, RepositoryManager manager);

    List<Product> findProductsByIds(List<Long> ids);

    List<Product> findAllProducts();

    void updateProduct(Product product);

    void deleteProduct(Product product);
}
