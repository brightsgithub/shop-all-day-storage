package com.shopallday.storage.infra.repository.products;

import com.shopallday.storage.domain.models.Product;
import com.shopallday.storage.domain.repository.products.ProductsRepository;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.infra.entities.ProductEntity;
import com.shopallday.storage.infra.mappers.ProductMapper;
import com.shopallday.storage.infra.repository.Merge;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;

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

    default void deleteProductById(Long id) {
        deleteById(id);
    }

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
}
