package com.shopallday.storage.infra.mappers;

import com.shopallday.storage.domain.models.Brand;
import com.shopallday.storage.infra.entities.BrandEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BrandMapper extends StorageMapper<BrandEntity, Brand>{

    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

    @Override
    BrandEntity mapToEntity(Brand brand);

    @Override
    Brand mapToDomain(BrandEntity brandEntity);

    @Override
    List<BrandEntity> mapToEntity(List<Brand> brands);

    @Override
    List<Brand> mapToDomain(List<BrandEntity> brandEntities);
}
