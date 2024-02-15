package com.shopallday.storage.infra.mappers;

import com.shopallday.storage.domain.models.ProductStock;
import com.shopallday.storage.infra.entities.ProductStockEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper(uses = ProductMapper.class)
public interface ProductStockMapper {

    ProductStockMapper INSTANCE = Mappers.getMapper(ProductStockMapper.class);

    @Mapping(source = "productEntity", target = "product")
    ProductStock mapToDomain(ProductStockEntity productStockEntity);

    @Mapping(source = "product", target = "productEntity")
    ProductStockEntity mapToEntity(ProductStock productStock);

    List<ProductStockEntity> mapToEntity(List<ProductStock> productStocks);
    List<ProductStock> mapToDomain(List<ProductStockEntity> productStockEntities);

}
