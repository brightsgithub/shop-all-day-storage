package com.shopallday.storage.infra.repository;

import com.shopallday.storage.domain.models.Product;
import com.shopallday.storage.domain.repository.ProductsRepository;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.infra.entities.BrandEntity;
import com.shopallday.storage.infra.entities.CategoryEntity;
import com.shopallday.storage.infra.entities.ProductEntity;
import com.shopallday.storage.infra.entities.ProductTypeEntity;
import com.shopallday.storage.infra.mappers.ProductMapper;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaProductsRepository extends JpaRepository<ProductEntity, Long>, ProductsRepository {

    ProductMapper mapper = ProductMapper.INSTANCE;

    default void createProduct(Product product) {
        final ProductEntity productEntity = mapper.productToProductEntity(product);
        save(productEntity);
    }

    @Override
    default void createProducts(List<Product> products, RepositoryManager manager) {
        final EntityManager entityManager = (EntityManager) manager.getManager();

        final List<ProductEntity> productEntities = mapper.productsToProductEntities(products);
        for (ProductEntity productEntity : productEntities) {
            BrandEntity brandEntity = entityManager.merge(productEntity.getBrandEntity());
            CategoryEntity categoryEntity = entityManager.merge(productEntity.getProductTypeEntity().getCategoryEntity());
            ProductTypeEntity productTypeEntity = entityManager.merge(productEntity.getProductTypeEntity());
            productTypeEntity.setCategoryEntity(categoryEntity);
            productEntity.setBrandEntity(brandEntity);
            productEntity.setProductTypeEntity(productTypeEntity);
        }

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
