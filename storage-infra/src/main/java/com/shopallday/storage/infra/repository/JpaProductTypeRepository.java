package com.shopallday.storage.infra.repository;

import com.shopallday.storage.domain.exceptions.product.ReadProductTypeException;
import com.shopallday.storage.domain.models.ProductType;
import com.shopallday.storage.domain.repository.ProductTypeRepository;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.infra.entities.ProductTypeEntity;
import com.shopallday.storage.infra.mappers.ProductTypeMapper;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaProductTypeRepository extends JpaRepository<ProductTypeEntity, Long>, ProductTypeRepository {

    ProductTypeMapper productTypeMapper = ProductTypeMapper.INSTANCE;

    @Override
    default void createProductType(ProductType productType) {
        final ProductTypeEntity productTypeEntity = productTypeMapper.mapToEntity(productType);
        save(productTypeEntity);
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

    default void createProductTypes(List<ProductType> productTypes, RepositoryManager repositoryManager) {
        final EntityManager entityManager = (EntityManager) repositoryManager.getManager();

        final List<ProductTypeEntity> productTypeEntities = productTypeMapper.mapToEntity(productTypes);

        Merge.mergeProductTypeEntity(entityManager, productTypeEntities);

        saveAll(productTypeEntities);

    }

    @Override
    default ProductType findProductTypeById(Long id) throws ReadProductTypeException {
        return productTypeMapper.mapToDomain(
                findById(id).orElseThrow(() -> new ReadProductTypeException("Id does not exist"
                )));
    }

    @Override
    default List<ProductType> findAllProductTypes() {
        return productTypeMapper.mapToDomain(findAll());
    }

    @Override
    default void updateProductType(ProductType productType) {
        createProductType(productType);
    }

    @Override
    default void deleteProductType(ProductType productType) {
        final ProductTypeEntity productTypeEntity = productTypeMapper.mapToEntity(productType);
        delete(productTypeEntity);
    }
}
