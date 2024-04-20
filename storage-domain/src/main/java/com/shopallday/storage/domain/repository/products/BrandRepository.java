package com.shopallday.storage.domain.repository.products;

import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.models.Brand;

import java.util.List;

public interface BrandRepository {

    void createBrands(List<Brand> brandList);
    Brand createBrand(Brand brand);

    List<Brand> findAllBrands();
    List<Brand> findBrandById(final Long id) throws ReadException;

    Brand updateBrand(Brand brand);

    void deleteBrandById(Long id);

    boolean isExists(Long id);

    void deleteAll();
}
