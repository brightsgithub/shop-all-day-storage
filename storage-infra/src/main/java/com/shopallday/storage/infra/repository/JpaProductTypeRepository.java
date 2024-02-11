package com.shopallday.storage.infra.repository;

import com.shopallday.storage.domain.exceptions.product.ReadProductTypeException;
import com.shopallday.storage.domain.models.ProductType;
import com.shopallday.storage.domain.repository.ProductTypeRepository;
import com.shopallday.storage.infra.entities.ProductTypeEntity;
import com.shopallday.storage.infra.mappers.ProductTypeMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaProductTypeRepository extends JpaRepository<ProductTypeEntity, Long>, ProductTypeRepository {

    ProductTypeMapper productTypeMapper = ProductTypeMapper.INSTANCE;

    @Override
    default void createProductType(ProductType productType) {
        final ProductTypeEntity productTypeEntity = productTypeMapper.productTypeToProductTypeEntity(productType);
        save(productTypeEntity);
    }

    @Override
    default void createProductTypes(List<ProductType> productTypes) {
        final List<ProductTypeEntity> productTypeEntities = productTypeMapper.productTypesToProductTypeEntities(productTypes);
        saveAll(productTypeEntities);
    }

    @Override
    default ProductType findProductTypeById(Long id) throws ReadProductTypeException {
        return productTypeMapper.productTypeEntityToProductType(
                findById(id).orElseThrow(() -> new ReadProductTypeException("Id does not exist"
                )));
    }

    @Override
    default List<ProductType> findAllProductTypes() {
        return productTypeMapper.productTypeEntitiesToProductTypes(findAll());
    }

    @Override
    default void updateProductType(ProductType productType) {
        createProductType(productType);
    }

    @Override
    default void deleteProductType(ProductType productType) {
        final ProductTypeEntity productTypeEntity = productTypeMapper.productTypeToProductTypeEntity(productType);
        delete(productTypeEntity);
    }
}
