package com.shopallday.storage.infra.repository.products;

import com.shopallday.storage.domain.models.Product;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.repository.products.ProductsRepository;
import com.shopallday.storage.infra.entities.ProductEntity;
import com.shopallday.storage.infra.mappers.ProductMapper;
import com.shopallday.storage.infra.repository.Merge;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface JpaProductsRepository extends JpaRepository<ProductEntity, Long>, ProductsRepository {

    ProductMapper mapper = ProductMapper.INSTANCE;

    default Product createProduct(Product product, RepositoryManager manager) {
        return mapper.mapToDomain(save(mergeProductEntity(product, manager)));
    }

    @Override
    default void createProducts(List<Product> products, RepositoryManager manager) {
        saveAll(mergeProductEntity(products, manager));
    }

    default List<Product> findProductsByIds(List<Long> ids) {
        return mapper
                .mapToDomain(findAllById(ids));
    }

    default List<Product> findAllProducts() {
        return mapper
                .mapToDomain(findAll());
    }

    default Product updateProduct(Product product, RepositoryManager manager) {
        return createProduct(product, manager);
    }

    default void deleteProductById(Long productId) {
        deleteProductStocksByProductId(productId); // needed since cascade remove does not seem to be having any effect
        deleteById(productId);
    }


    @Modifying // needed since this is not a select statement
    @Query("DELETE FROM ProductStockEntity ps WHERE ps.productEntity.productId = :productId")
    void deleteProductStocksByProductId(@Param("productId") Long productId);

    static List<ProductEntity> mergeProductEntity(List<Product> products, RepositoryManager manager) {
        List<ProductEntity> productEntities = new ArrayList<>();
        for (Product product : products) {
            productEntities.add(mergeProductEntity(product, manager));
        }
        return productEntities;
    }

    static ProductEntity mergeProductEntity(Product product, RepositoryManager manager) {
        final EntityManager entityManager = (EntityManager) manager.getManager();
        final ProductEntity productEntity = mapper.mapToEntity(product);
        Merge.mergeProductEntity(entityManager,productEntity);
        return productEntity;
    }

    @Override
    default boolean isExists(Long id) {
        return existsById(id);
    }
    @Override
    void deleteAll();
}
