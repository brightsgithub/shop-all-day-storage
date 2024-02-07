package com.shopallday.storage.infra.mappers;

import com.shopallday.storage.domain.models.ProductType;
import com.shopallday.storage.infra.entities.ProductTypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = CategoryMapper.class) // needed for child mappings
public interface ProductTypeMapper {

    ProductTypeMapper INSTANCE = Mappers.getMapper(ProductTypeMapper.class);

    @Mapping(source = "category", target = "categoryEntity") // needed for child mappings
    ProductTypeEntity productTypeToProductTypeEntity(ProductType productType);

    @Mapping(source = "categoryEntity", target = "category") // needed for child mappings
    ProductType productTypeEntityToProductType(ProductTypeEntity productTypeEntity);

    List<ProductTypeEntity> productTypesToProductTypeEntities(List<ProductType> productTypes);
    List<ProductType> productTypeEntitiesToProductTypes(List<ProductTypeEntity> productTypeEntities);
}
