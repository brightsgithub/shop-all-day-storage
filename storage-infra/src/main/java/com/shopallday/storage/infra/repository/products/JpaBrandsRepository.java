package com.shopallday.storage.infra.repository.products;

import com.shopallday.storage.domain.models.Brand;
import com.shopallday.storage.domain.repository.products.BrandRepository;
import com.shopallday.storage.infra.entities.BrandEntity;
import com.shopallday.storage.infra.mappers.BrandMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaBrandsRepository extends JpaRepository<BrandEntity, Long>, BrandRepository {

    BrandMapper mapper = BrandMapper.INSTANCE;

    @Override
    default void createBrands(List<Brand> brandList) {
        final List<BrandEntity> brandEntities = mapper.mapToEntity(brandList);
        saveAll(brandEntities);
    }

    @Override
    default Brand createBrand(Brand brand) {
        final BrandEntity brandEntity = mapper.mapToEntity(brand);
        return mapper.mapToDomain(save(brandEntity));
    }

    @Override
    default List<Brand> findAllBrands() {
        return mapper
                .mapToDomain(findAll());
    }

    @Override
    default List<Brand> findBrandById(final Long id) {
        return mapper
                .mapToDomain(findAllById(List.of(id)));
    }

    @Override
    default Brand updateBrand(Brand brand) {
        return createBrand(brand);
    }

    @Override
    default void deleteBrandById(Long id) {
        final List<Long> productIds = getProductIdConstraints(id);
        deleteOrderLinesByProductIds(productIds);
        deleteProductStocksByProductIds(productIds);
        deleteProductsByIds(productIds);
        deleteById(id);
    }

    @Query("SELECT distinct " +
            "p.productId " +
            "FROM BrandEntity b," +
            "ProductEntity p," +
            "ProductStockEntity ps " +
            "WHERE ps.productEntity.productId = p.productId " +
            "AND p.brandEntity.brandId = b.brandId " +
            "AND b.brandId = :brandId")
    List<Long> getProductIdConstraints(@Param("brandId") Long brandId);

    @Modifying // needed since this is not a select statement
    @Query("DELETE FROM OrderLineEntity ol WHERE ol.productEntity.productId in :productIds")
    void deleteOrderLinesByProductIds(@Param("productIds") List<Long> productIds);
    @Modifying // needed since this is not a select statement
    @Query("DELETE FROM ProductStockEntity ps WHERE ps.productEntity.productId in :productIds")
    void deleteProductStocksByProductIds(@Param("productIds") List<Long> productIds);
    @Modifying // needed since this is not a select statement
    @Query("DELETE FROM ProductEntity p WHERE p.productId in :productIds")
    void deleteProductsByIds(@Param("productIds") List<Long> productIds);

    @Override
    default boolean isExists(Long id) {
        return existsById(id);
    }
}
