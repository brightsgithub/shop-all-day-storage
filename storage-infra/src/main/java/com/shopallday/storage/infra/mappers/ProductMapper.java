package com.shopallday.storage.infra.mappers;

import com.shopallday.storage.domain.models.Product;
import com.shopallday.storage.infra.entities.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "productType", target = "productTypeEntity")
    @Mapping(source = "brand", target = "brandEntity")
    ProductEntity productToProductEntity(Product product);
    @Mapping(source = "productTypeEntity", target = "productType")
    @Mapping(source = "brandEntity", target = "brand")
    Product productEntityToProduct(ProductEntity productEntity);

    List<ProductEntity> productsToProductEntities(List<Product> products);
    List<Product> productEntitiesToProducts(List<ProductEntity> productEntities);
}
