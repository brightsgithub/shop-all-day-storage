package com.shopallday.storage.infra.repository;

import com.shopallday.storage.domain.models.Brand;
import com.shopallday.storage.domain.repository.BrandRepository;
import com.shopallday.storage.infra.entities.BrandEntity;
import com.shopallday.storage.infra.mappers.BrandMapper;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public interface JpaBrandsRepository extends CrudRepository<BrandEntity, Long>, BrandRepository {

    BrandMapper mapper = BrandMapper.INSTANCE;

    @Override
    default void createBrands(List<Brand> brandList) {
        final List<BrandEntity> brandEntities = mapper.brandsToBrandEntities(brandList);
        saveAll(brandEntities);
    }

    @Override
    default void createBrand(Brand brand) {
        final BrandEntity brandEntity = mapper.brandToBrandEntity(brand);
        save(brandEntity);
    }

    @Override
    default List<Brand> findAllBrands() {
        return mapper
                .brandEntitiesToBrands(StreamSupport.stream(findAll().spliterator(), false)
                        .collect(Collectors.toList()));
    }

    @Override
    default List<Brand> findBrandById(final List<Long> ids) {
        return mapper
                .brandEntitiesToBrands(StreamSupport.stream(findAllById(ids).spliterator(), false)
                        .collect(Collectors.toList()));
    }

    @Override
    default void updateBrand(Brand brand) {
        createBrand(brand);
    }

    @Override
    default void deleteBrand(Brand brand) {
        final BrandEntity brandEntity = mapper.brandToBrandEntity(brand);
        delete(brandEntity);
    }
}
