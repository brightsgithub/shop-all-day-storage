package com.shopallday.storage.app.services.products;

import com.shopallday.storage.app.models.BrandDto;
import com.shopallday.storage.domain.exceptions.crud.CreateException;
import com.shopallday.storage.domain.exceptions.crud.DeleteException;
import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.exceptions.crud.UpdateException;

import java.util.List;
import java.util.Map;

public interface BrandsService {
    BrandDto createBrand(BrandDto brandDto) throws CreateException;
    List<BrandDto> getBrands();
    BrandDto getBrandById(Long id) throws ReadException;
    BrandDto updateBrand(BrandDto brandDto) throws ReadException, UpdateException;
    void deleteBrandById(Long id) throws DeleteException;
    BrandDto partialUpdateBrand(Long id, Map<String, Object> fields)
            throws ReadException, UpdateException;
}
