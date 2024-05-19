package com.shopallday.storage.app.services.products;

import com.shopallday.storage.app.mappers.Mapper;
import com.shopallday.storage.app.models.ProductTypeDto;
import com.shopallday.storage.app.services.BaseService;
import com.shopallday.storage.domain.exceptions.crud.CreateException;
import com.shopallday.storage.domain.exceptions.crud.DeleteException;
import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.exceptions.crud.UpdateException;
import com.shopallday.storage.domain.models.ProductType;
import com.shopallday.storage.domain.usecases.producttype.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ProductTypeServiceImpl extends BaseService implements ProductTypeService {

    private final CreateProductTypeUseCase createProductTypeUseCase;
    private final CreateSingleProductTypeUseCase createSingleProductTypeUseCase;
    private final DeleteProductTypeUseCase deleteProductTypeUseCase;
    private final GetProductTypeByIdUseCase getProductTypeByIdUseCase;
    private final UpdateProductTypeUseCase updateProductTypeUseCase;
    private final GetAllProductTypesUseCase getAllProductTypesUseCase;
    private final GetProductTypesByCategoryIdUseCase getProductTypesByCategoryIdUseCase;

    @Qualifier("productTypeMapper")
    private Mapper<ProductType, ProductTypeDto> mapper;

    public ProductTypeServiceImpl(CreateProductTypeUseCase createProductTypeUseCase,
                                  CreateSingleProductTypeUseCase createSingleProductTypeUseCase,
                                  DeleteProductTypeUseCase deleteProductTypeUseCase,
                                  GetProductTypeByIdUseCase getProductTypeByIdUseCase,
                                  UpdateProductTypeUseCase updateProductTypeUseCase,
                                  GetAllProductTypesUseCase getAllProductTypesUseCase,
                                  @Qualifier("productTypeMapper") Mapper<ProductType, ProductTypeDto> mapper,
                                  GetProductTypesByCategoryIdUseCase getProductTypesByCategoryIdUseCase) {
        this.createProductTypeUseCase = createProductTypeUseCase;
        this.createSingleProductTypeUseCase = createSingleProductTypeUseCase;
        this.deleteProductTypeUseCase = deleteProductTypeUseCase;
        this.getProductTypeByIdUseCase = getProductTypeByIdUseCase;
        this.updateProductTypeUseCase = updateProductTypeUseCase;
        this.getAllProductTypesUseCase = getAllProductTypesUseCase;
        this.getProductTypesByCategoryIdUseCase = getProductTypesByCategoryIdUseCase;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public ProductTypeDto createProductType(ProductTypeDto productTypeDto) throws CreateException {
        final ProductType productType = mapper.mapFromDtoToDomain(productTypeDto);
        final ProductType justCreatedProductType = createSingleProductTypeUseCase.execute(productType);
        return mapper.mapFromDomainToDto(justCreatedProductType);
    }

    @Override
    @Transactional
    public List<ProductTypeDto> getProductTypes() {
        return mapper.mapFromDomainToDto(getAllProductTypesUseCase.execute());
    }

    @Override
    @Transactional
    public ProductTypeDto getProductTypeById(Long id) throws ReadException {
        final ProductType productType = getProductTypeByIdUseCase.execute(id);
        return mapper.mapFromDomainToDto(productType);
    }

    @Override
    @Transactional
    public ProductTypeDto updateProductType(ProductTypeDto productTypeDto) throws ReadException, UpdateException {
        final ProductType productType = mapper.mapFromDtoToDomain(productTypeDto);
        final ProductType updatedProductType = updateProductTypeUseCase.execute(productType);
        return mapper.mapFromDomainToDto(updatedProductType);
    }

    @Override
    @Transactional
    public void deleteProductTypeById(Long id) throws DeleteException {
        deleteProductTypeUseCase.execute(id);
    }
    @Override
    @Transactional
    public List<ProductTypeDto> getProductTypesByCategoryId(Long id) throws ReadException {
        return mapper.mapFromDomainToDto(getProductTypesByCategoryIdUseCase.execute(id));
    }

    @Override
    @Transactional
    public ProductTypeDto partiallyUpdateProductType(Long id, Map<String, Object> fields)
            throws ReadException, UpdateException {
        ProductType existingProductType = getProductTypeByIdUseCase.execute(id);
        updateFieldsOnObject(fields, existingProductType, ProductType.class);
        return updateProductType(mapper.mapFromDomainToDto(existingProductType));
    }
}
