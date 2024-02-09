package com.shopallday.storage.domain.repository;

import com.shopallday.storage.domain.exceptions.product.ReadBrandException;
import com.shopallday.storage.domain.models.Brand;

import java.util.List;

public interface BrandRepository {

    void createBrands(List<Brand> brandList);
    void createBrand(Brand brand);

    List<Brand> findAllBrands();
    List<Brand> findBrandById(final List<Long> ids) throws ReadBrandException;

    void updateBrand(Brand brand);

    void deleteBrand(Brand brand);

}
