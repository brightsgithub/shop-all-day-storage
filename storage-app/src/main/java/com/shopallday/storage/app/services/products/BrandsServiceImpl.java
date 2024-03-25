package com.shopallday.storage.app.services.products;

import com.shopallday.storage.app.mappers.Mapper;
import com.shopallday.storage.app.models.BrandDto;
import com.shopallday.storage.app.services.BaseService;
import com.shopallday.storage.domain.exceptions.crud.CreateException;
import com.shopallday.storage.domain.exceptions.crud.DeleteException;
import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.exceptions.crud.UpdateException;
import com.shopallday.storage.domain.models.Brand;
import com.shopallday.storage.domain.usecases.brand.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class BrandsServiceImpl extends BaseService implements BrandsService {
    private final GetAllBrandsUseCase getAllBrandsUseCase;
    private final CreateSingleBrandUseCase createSingleBrandUseCase;
    private final GetBrandByIdUseCase getBrandByIdUseCase;
    private final UpdateBrandsUseCase updateBrandsUseCase;
    private final DeleteBrandsUseCase deleteBrandsUseCase;
    @Qualifier("brandMapper")
    private Mapper<Brand, BrandDto> brandMapper;

    public BrandsServiceImpl(GetAllBrandsUseCase getAllBrandsUseCase,
                             CreateSingleBrandUseCase createSingleBrandUseCase,
                             GetBrandByIdUseCase getBrandByIdUseCase, UpdateBrandsUseCase updateBrandsUseCase,
                             DeleteBrandsUseCase deleteBrandsUseCase,
                             @Qualifier("brandMapper") Mapper<Brand, BrandDto> brandMapper) {
        this.getAllBrandsUseCase = getAllBrandsUseCase;
        this.createSingleBrandUseCase = createSingleBrandUseCase;
        this.getBrandByIdUseCase = getBrandByIdUseCase;
        this.updateBrandsUseCase = updateBrandsUseCase;
        this.deleteBrandsUseCase = deleteBrandsUseCase;
        this.brandMapper = brandMapper;
    }

    @Override
    @Transactional
    public BrandDto createBrand(BrandDto brandDto) throws CreateException {
        final Brand brand = brandMapper.mapFromDtoToDomain(brandDto);
        final Brand justCreatedBrand = createSingleBrandUseCase.execute(brand);
        return brandMapper.mapFromDomainToDto(justCreatedBrand);
    }

    @Override
    @Transactional
    public List<BrandDto> getBrands() {
        return brandMapper.mapFromDomainToDto(getAllBrandsUseCase.execute());
    }

    @Override
    @Transactional
    public BrandDto getBrandById(Long id) throws ReadException {
        final Brand brand = getBrandByIdUseCase.execute(id);
        return brandMapper.mapFromDomainToDto(brand);
    }

    @Override
    @Transactional
    public BrandDto updateBrand(BrandDto brandDto) throws ReadException, UpdateException {
        final Brand brand = brandMapper.mapFromDtoToDomain(brandDto);
        final Brand updatedBrand = updateBrandsUseCase.execute(brand);
        return brandMapper.mapFromDomainToDto(updatedBrand);
    }

    @Override
    @Transactional
    public void deleteBrandById(Long id) throws DeleteException {
        deleteBrandsUseCase.execute(id);
    }

    @Override
    @Transactional
    public BrandDto partialUpdateBrand(Long id, Map<String, Object> fields)
            throws ReadException, UpdateException {
        Brand existingBrand = getBrandByIdUseCase.execute(id);
        updateFieldsOnObject(fields, existingBrand, Brand.class);
        return updateBrand(brandMapper.mapFromDomainToDto(existingBrand));
    }
}
