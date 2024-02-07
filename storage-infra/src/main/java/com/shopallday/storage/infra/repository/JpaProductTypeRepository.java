package com.shopallday.storage.infra.repository;

import com.shopallday.storage.domain.exceptions.product.ReadProductTypeException;
import com.shopallday.storage.domain.models.ProductType;
import com.shopallday.storage.domain.repository.ProductTypeRepository;
import com.shopallday.storage.infra.entities.ProductTypeEntity;
import com.shopallday.storage.infra.mappers.ProductTypeMapper;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public interface JpaProductTypeRepository extends CrudRepository<ProductTypeEntity, Long>, ProductTypeRepository {

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
        return productTypeMapper.productTypeEntitiesToProductTypes(StreamSupport.stream(findAll().spliterator(), false)
                .collect(Collectors.toList()));
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
