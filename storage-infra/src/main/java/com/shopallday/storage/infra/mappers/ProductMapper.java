package com.shopallday.storage.infra.mappers;

import com.shopallday.storage.domain.models.Product;
import com.shopallday.storage.infra.entities.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper extends StorageMapper<ProductEntity, Product>{

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Override
    @Mapping(source = "productType.category", target = "productTypeEntity.categoryEntity")
    @Mapping(source = "productType", target = "productTypeEntity")
    @Mapping(source = "brand", target = "brandEntity")
    ProductEntity mapToEntity(Product product);

    @Override
    @Mapping(source = "productTypeEntity.categoryEntity", target = "productType.category")
    @Mapping(source = "productTypeEntity", target = "productType")
    @Mapping(source = "brandEntity", target = "brand")
    Product mapToDomain(ProductEntity productEntity);

    @Override
    List<ProductEntity> mapToEntity(List<Product> products);

    @Override
    List<Product> mapToDomain(List<ProductEntity> productEntities);
}
