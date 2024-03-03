package com.shopallday.storage.app.mappers;

import com.shopallday.storage.app.models.ProductDto;
import com.shopallday.storage.domain.models.Product;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@org.mapstruct.Mapper
public interface ProductMapper extends Mapper<Product, ProductDto> {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Override
    ProductDto mapFromDomainToDto(Product domain);

    @Override
    Product mapFromDtoToDomain(ProductDto dto);

    @Override
    List<ProductDto> mapFromDomainToDto(List<Product> domainList);

    @Override
    List<Product> mapFromDtoToDomain(List<ProductDto> dtoList);
}
