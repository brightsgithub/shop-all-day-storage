package com.shopallday.storage.infra.repository.products;

import com.shopallday.storage.domain.models.Product;
import com.shopallday.storage.domain.repository.ProductsRepository;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.infra.entities.ProductEntity;
import com.shopallday.storage.infra.mappers.ProductMapper;
import com.shopallday.storage.infra.repository.Merge;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaProductsRepository extends JpaRepository<ProductEntity, Long>, ProductsRepository {

    ProductMapper mapper = ProductMapper.INSTANCE;

    default void createProduct(Product product) {
        final ProductEntity productEntity = mapper.mapToEntity(product);
        save(productEntity);
    }

    @Override
    default void createProducts(List<Product> products, RepositoryManager manager) {
        final EntityManager entityManager = (EntityManager) manager.getManager();

        final List<ProductEntity> productEntities = mapper.mapToEntity(products);
        Merge.mergeProductEntity(entityManager,productEntities);

        saveAll(productEntities);
    }

    default List<Product> findProductsByIds(List<Long> ids) {
        return mapper
                .mapToDomain(findAllById(ids));
    }

    default List<Product> findAllProducts() {
        return mapper
                .mapToDomain(findAll());
    }

    default void updateProduct(Product product) {
        createProduct(product);
    }

    default void deleteProduct(Product product) {
        final ProductEntity productEntity = mapper.mapToEntity(product);
        delete(productEntity);
    }
}
