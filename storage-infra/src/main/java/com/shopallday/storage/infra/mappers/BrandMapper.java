package com.shopallday.storage.infra.mappers;

import com.shopallday.storage.domain.models.Brand;
import com.shopallday.storage.infra.entities.BrandEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BrandMapper {

    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

    BrandEntity mapToEntity(Brand brand);
    Brand mapToDomain(BrandEntity brandEntity);

    List<BrandEntity> mapToEntity(List<Brand> brands);
    List<Brand> mapToDomain(List<BrandEntity> brandEntities);

}
