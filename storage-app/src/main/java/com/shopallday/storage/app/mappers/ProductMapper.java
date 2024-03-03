package com.shopallday.storage.app.mappers;

import com.shopallday.storage.app.models.ProductDto;
import com.shopallday.storage.domain.models.Product;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@org.mapstruct.Mapper
public interface ProductMapper extends Mapper<Product, ProductDto> {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Override
    @Mapping(source = "productType.category", target = "productTypeDto.categoryDto")
    @Mapping(source = "productType", target = "productTypeDto")
    @Mapping(source = "brand", target = "brandDto")
    ProductDto mapFromDomainToDto(Product domain);

    @Override
    @Mapping(source = "productTypeDto.categoryDto", target = "productType.category")
    @Mapping(source = "productTypeDto", target = "productType")
    @Mapping(source = "brandDto", target = "brand")
    Product mapFromDtoToDomain(ProductDto dto);

    @Override
    List<ProductDto> mapFromDomainToDto(List<Product> domainList);

    @Override
    List<Product> mapFromDtoToDomain(List<ProductDto> dtoList);
}
