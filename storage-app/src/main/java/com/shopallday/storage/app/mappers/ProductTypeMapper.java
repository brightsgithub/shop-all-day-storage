package com.shopallday.storage.app.mappers;

import com.shopallday.storage.app.models.ProductTypeDto;
import com.shopallday.storage.domain.models.ProductType;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@org.mapstruct.Mapper
public interface ProductTypeMapper extends Mapper<ProductType, ProductTypeDto> {
    ProductTypeMapper INSTANCE = Mappers.getMapper(ProductTypeMapper.class);

    @Mapping(source = "category", target = "categoryDto") // needed for child mappings
    @Override
    ProductTypeDto mapFromDomainToDto(ProductType domain);

    @Mapping(source = "categoryDto", target = "category") // needed for child mappings
    @Override
    ProductType mapFromDtoToDomain(ProductTypeDto Dto);

    @Override
    List<ProductTypeDto> mapFromDomainToDto(List<ProductType> domains);

    @Override
    List<ProductType> mapFromDtoToDomain(List<ProductTypeDto> dtos);
}
