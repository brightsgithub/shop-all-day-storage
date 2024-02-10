package com.shopallday.storage.infra.repository;

import com.shopallday.storage.domain.models.Product;
import com.shopallday.storage.domain.repository.ProductsRepository;
import com.shopallday.storage.infra.entities.ProductEntity;
import com.shopallday.storage.infra.mappers.ProductMapper;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public interface JpaProductsRepository extends CrudRepository<ProductEntity, Long>, ProductsRepository {

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
                .productEntitiesToProducts(StreamSupport.stream(findAllById(ids).spliterator(), false)
                        .collect(Collectors.toList()));
    }

    default List<Product> findAllProducts() {
        return mapper
                .productEntitiesToProducts(StreamSupport.stream(findAll().spliterator(), false)
                        .collect(Collectors.toList()));
    }

    default void updateProduct(Product product) {
        createProduct(product);
    }

    default void deleteProduct(Product product) {
        final ProductEntity productEntity = mapper.productToProductEntity(product);
        delete(productEntity);
    }
}
