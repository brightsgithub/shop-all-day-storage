package com.shopallday.storage.domain.repository.products;

import com.shopallday.storage.domain.models.Product;
import com.shopallday.storage.domain.repository.RepositoryManager;

import java.util.List;

public interface ProductsRepository {

    Product createProduct(Product product, RepositoryManager manager);

    void createProducts(List<Product> products, RepositoryManager manager);

    List<Product> findProductsByIds(List<Long> ids);

    List<Product> findAllProducts();

    Product updateProduct(Product product, RepositoryManager manager);

    void deleteProductById(Long id);

    boolean isExists(Long id);
}
