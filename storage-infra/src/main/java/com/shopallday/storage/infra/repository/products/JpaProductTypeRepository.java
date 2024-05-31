package com.shopallday.storage.infra.repository.products;

import com.shopallday.storage.domain.exceptions.BusinessErrorCodes;
import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.models.ProductType;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.repository.products.ProductTypeRepository;
import com.shopallday.storage.infra.entities.ProductTypeEntity;
import com.shopallday.storage.infra.mappers.ProductTypeMapper;
import com.shopallday.storage.infra.repository.Merge;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaProductTypeRepository extends JpaRepository<ProductTypeEntity, Long>, ProductTypeRepository {

    ProductTypeMapper productTypeMapper = ProductTypeMapper.INSTANCE;

    @Override
    default ProductType createProductType(ProductType productType, RepositoryManager repositoryManager) {
        final EntityManager entityManager = (EntityManager) repositoryManager.getManager();

        final ProductTypeEntity productTypeEntity = productTypeMapper.mapToEntity(productType);

        Merge.mergeProductTypeEntity(entityManager, productTypeEntity);

        return productTypeMapper.mapToDomain(save(productTypeEntity));
    }

    /*
        Error Message: "Caused by: org.hibernate.PersistentObjectException: detached entity passed to persist:
        com.shopallday.storage.infra.entities.CategoryEntity"
        Meaning: You're trying to persist a CategoryEntity that's already detached from the current Hibernate session,
        meaning Hibernate doesn't manage it and can't track its changes.

        Root Cause:
        The categories list retrieved from categoryRepository.getCategories() contains detached Category entities.
        When these detached entities are attached to new ProductTypes and saved, Hibernate tries to persist them as well,
        causing the error. This is because I'm now using cascade = CascadeType.PERSIST.

        Solution: use merge to reattach them to the context

         */

    default List<ProductType> createProductTypes(List<ProductType> productTypes, RepositoryManager repositoryManager) {
        final EntityManager entityManager = (EntityManager) repositoryManager.getManager();

        final List<ProductTypeEntity> productTypeEntities = productTypeMapper.mapToEntity(productTypes);

        Merge.mergeProductTypeEntity(entityManager, productTypeEntities);

        return productTypeMapper.mapToDomain(saveAll(productTypeEntities));
    }

    @Override
    default ProductType findProductTypeById(Long id) throws ReadException {
        return productTypeMapper.mapToDomain(
                findById(id).orElseThrow(() -> new ReadException(
                        "Id "+id+" does not exist", BusinessErrorCodes.PRODUCT_TYPE_NOT_FOUND
                )));
    }

    @Override
    default List<ProductType> findAllProductTypes() {
        return productTypeMapper.mapToDomain(findAll());
    }

    @Override
    default ProductType updateProductType(ProductType productType, RepositoryManager repositoryManager) {
        return createProductType(productType, repositoryManager);
    }

    @Override
    default void deleteProductTypeById(Long id) {
        final List<Long> productIds = getProductIdConstraints(id);
        deleteOrderLinesByProductIds(productIds);
        deleteProductStocksByProductIds(productIds);
        deleteProductsByIds(productIds);
        deleteById(id);
    }

    @Query("SELECT distinct " +
            "p.productId " +
            "FROM ProductTypeEntity pt," +
            "ProductEntity p," +
            "ProductStockEntity ps " +
            "WHERE pt.productTypeId = p.productTypeEntity.productTypeId " +
            "AND p.productId = ps.productEntity.productId " +
            "AND pt.productTypeId = :productTypeId")
    List<Long> getProductIdConstraints(@Param("productTypeId") Long productTypeId);

    @Modifying // needed since this is not a select statement
    @Query("DELETE FROM OrderLineEntity ol WHERE ol.productEntity.productId in :productIds")
    void deleteOrderLinesByProductIds(@Param("productIds") List<Long> productIds);
    @Modifying // needed since this is not a select statement
    @Query("DELETE FROM ProductStockEntity ps WHERE ps.productEntity.productId in :productIds")
    void deleteProductStocksByProductIds(@Param("productIds") List<Long> productIds);
    @Modifying // needed since this is not a select statement
    @Query("DELETE FROM ProductEntity p WHERE p.productId in :productIds")
    void deleteProductsByIds(@Param("productIds") List<Long> productIds);

    // Custom query method to find ProductTypes by category ID

    default List<ProductType> findProductTypesByCategoryId(final Long categoryId) {
        List<ProductTypeEntity> entities = findProductTypesByCategoryIdInternal(categoryId);
        return productTypeMapper.mapToDomain(entities);
    }
    @Query("SELECT pt FROM ProductTypeEntity pt WHERE pt.categoryEntity.categoryId = :categoryId")
    List<ProductTypeEntity> findProductTypesByCategoryIdInternal(@Param("categoryId") Long categoryId);

    @Override
    default boolean isExists(Long id) {
        return existsById(id);
    }
    @Override
    void deleteAll();
}
