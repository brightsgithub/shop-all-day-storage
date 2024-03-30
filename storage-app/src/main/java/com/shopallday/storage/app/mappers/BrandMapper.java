package com.shopallday.storage.app.mappers;

import com.shopallday.storage.app.models.BrandDto;
import com.shopallday.storage.domain.models.Brand;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@org.mapstruct.Mapper
public interface BrandMapper extends Mapper<Brand, BrandDto> {
    @Override
    BrandDto mapFromDomainToDto(Brand domain);

    @Override
    Brand mapFromDtoToDomain(BrandDto Dto);

    @Override
    List<BrandDto> mapFromDomainToDto(List<Brand> domains);

    @Override
    List<Brand> mapFromDtoToDomain(List<BrandDto> dtos);
}
