package com.shopallday.storage.infra.repository;

import com.shopallday.storage.domain.models.Product;
import com.shopallday.storage.domain.repository.ProductsRepository;
import com.shopallday.storage.infra.entities.ProductEntity;
import com.shopallday.storage.infra.mappers.ProductMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaProductsRepository extends JpaRepository<ProductEntity, Long>, ProductsRepository {

    ProductMapper mapper = ProductMapper.INSTANCE;

    default void createProduct(Product product) {
        final ProductEntity productEntity = mapper.productToProductEntity(product);
        save(productEntity);
    }
    default void createProducts(List<Product> products) {
        final List<ProductEntity> productEntities = mapper.productsToProductEntities(products);
        saveAll(productEntities);
    }

    default List<Product> findProductsByIds(List<Long> ids) {
        return mapper
                .productEntitiesToProducts(findAllById(ids));
    }

    default List<Product> findAllProducts() {
        return mapper
                .productEntitiesToProducts(findAll());
    }

    default void updateProduct(Product product) {
        createProduct(product);
    }

    default void deleteProduct(Product product) {
        final ProductEntity productEntity = mapper.productToProductEntity(product);
        delete(productEntity);
    }
}
