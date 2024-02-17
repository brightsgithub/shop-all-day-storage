package com.shopallday.storage.infra.repository.products;

import com.shopallday.storage.domain.models.Brand;
import com.shopallday.storage.domain.repository.BrandRepository;
import com.shopallday.storage.infra.entities.BrandEntity;
import com.shopallday.storage.infra.mappers.BrandMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaBrandsRepository extends JpaRepository<BrandEntity, Long>, BrandRepository {

    BrandMapper mapper = BrandMapper.INSTANCE;

    @Override
    default void createBrands(List<Brand> brandList) {
        final List<BrandEntity> brandEntities = mapper.mapToEntity(brandList);
        saveAll(brandEntities);
    }

    @Override
    default void createBrand(Brand brand) {
        final BrandEntity brandEntity = mapper.mapToEntity(brand);
        save(brandEntity);
    }

    @Override
    default List<Brand> findAllBrands() {
        return mapper
                .mapToDomain(findAll());
    }

    @Override
    default List<Brand> findBrandById(final List<Long> ids) {
        return mapper
                .mapToDomain(findAllById(ids));
    }

    @Override
    default void updateBrand(Brand brand) {
        createBrand(brand);
    }

    @Override
    default void deleteBrand(Brand brand) {
        final BrandEntity brandEntity = mapper.mapToEntity(brand);
        delete(brandEntity);
    }
}
