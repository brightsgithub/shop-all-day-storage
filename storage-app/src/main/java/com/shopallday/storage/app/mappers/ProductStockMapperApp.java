package com.shopallday.storage.app.mappers;

import com.shopallday.storage.app.models.ProductStockDto;
import com.shopallday.storage.domain.models.ProductStock;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@org.mapstruct.Mapper(uses = ProductMapper.class)
public interface ProductStockMapperApp extends Mapper<ProductStock, ProductStockDto> {
    ProductStockMapperApp INSTANCE = Mappers.getMapper(ProductStockMapperApp.class);

    @Override
    @Mapping(source = "product", target = "productDto")
    ProductStockDto mapFromDomainToDto(ProductStock domain);

    @Override
    @Mapping(source = "productDto", target = "product")
    ProductStock mapFromDtoToDomain(ProductStockDto Dto);

    @Override
    List<ProductStockDto> mapFromDomainToDto(List<ProductStock> domains);

    @Override
    List<ProductStock> mapFromDtoToDomain(List<ProductStockDto> dtos);
}