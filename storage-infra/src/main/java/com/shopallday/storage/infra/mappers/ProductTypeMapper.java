package com.shopallday.storage.infra.mappers;

import com.shopallday.storage.domain.models.ProductType;
import com.shopallday.storage.infra.entities.ProductTypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = CategoryMapper.class) // needed for child mappings
public interface ProductTypeMapper extends StorageMapper<ProductTypeEntity, ProductType> {

    ProductTypeMapper INSTANCE = Mappers.getMapper(ProductTypeMapper.class);
    @Mapping(source = "category", target = "categoryEntity") // needed for child mappings
    @Override
    ProductTypeEntity mapToEntity(ProductType productType);

    @Mapping(source = "categoryEntity", target = "category") // needed for child mappings
    @Override
    ProductType mapToDomain(ProductTypeEntity productTypeEntity);

    @Override
    List<ProductTypeEntity> mapToEntity(List<ProductType> productTypes);

    @Override
    List<ProductType> mapToDomain(List<ProductTypeEntity> productTypeEntities);
}
