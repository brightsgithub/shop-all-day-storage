package com.shopallday.storage.app.services.products;

import com.shopallday.storage.app.mappers.Mapper;
import com.shopallday.storage.app.models.ProductStockDto;
import com.shopallday.storage.app.services.BaseService;
import com.shopallday.storage.domain.exceptions.crud.CreateException;
import com.shopallday.storage.domain.exceptions.crud.DeleteException;
import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.exceptions.crud.UpdateException;
import com.shopallday.storage.domain.models.Brand;
import com.shopallday.storage.domain.models.ProductStock;
import com.shopallday.storage.domain.usecases.productstock.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductStockServiceImpl extends BaseService implements ProductStockService {
    private final GetAllProductStockUseCase getAllProductStockUseCase;
    private final CreateSingleProductStockUseCase createSingleProductStockUseCase;
    private final GetProductStockByIdUseCase getProductStockByIdUseCase;
    private final UpdateProductStockUseCase updateProductStockUseCase;
    private final DeleteProductStockUseCase deleteProductStockUseCase;
    @Qualifier("productStockMapper")
    private final Mapper<ProductStock, ProductStockDto> mapper;

    public ProductStockServiceImpl(GetAllProductStockUseCase getAllProductStockUseCase,
                                   CreateSingleProductStockUseCase createSingleProductStockUseCase,
                                   GetProductStockByIdUseCase getProductStockByIdUseCase,
                                   UpdateProductStockUseCase updateProductStockUseCase,
                                   DeleteProductStockUseCase deleteProductStockUseCase,
                                   @Qualifier("productStockMapper") Mapper<ProductStock, ProductStockDto> mapper) {
        this.getAllProductStockUseCase = getAllProductStockUseCase;
        this.createSingleProductStockUseCase = createSingleProductStockUseCase;
        this.getProductStockByIdUseCase = getProductStockByIdUseCase;
        this.updateProductStockUseCase = updateProductStockUseCase;
        this.deleteProductStockUseCase = deleteProductStockUseCase;
        this.mapper = mapper;
    }


    @Override
    @Transactional
    public ProductStockDto createProductStock(ProductStockDto productStockDto) throws CreateException {
        final ProductStock productStock = mapper.mapFromDtoToDomain(productStockDto);
        final ProductStock justCreatedProductStock = createSingleProductStockUseCase.execute(productStock);
        return mapper.mapFromDomainToDto(justCreatedProductStock);
    }

    @Override
    @Transactional
    public List<ProductStockDto> getProductStocks() {
        return mapper.mapFromDomainToDto(getAllProductStockUseCase.execute());
    }

    @Override
    @Transactional
    public ProductStockDto getProductStockById(Long id) throws ReadException {
        final ProductStock productStock = getProductStockByIdUseCase.execute(id);
        return mapper.mapFromDomainToDto(productStock);
    }

    @Override
    @Transactional
    public ProductStockDto updateProductStock(ProductStockDto productStockDto) throws ReadException, UpdateException {
        final ProductStock productStock = mapper.mapFromDtoToDomain(productStockDto);
        final ProductStock updatedProductStock = updateProductStockUseCase.execute(productStock);
        return mapper.mapFromDomainToDto(updatedProductStock);
    }

    @Override
    @Transactional
    public void deleteProductStockById(Long id) throws DeleteException {
        deleteProductStockUseCase.execute(id);
    }

    @Override
    @Transactional
    public ProductStockDto partiallyUpdateProductStock(Long id, Map<String, Object> fields)
            throws ReadException, UpdateException {
        ProductStock existingProductStock = getProductStockByIdUseCase.execute(id);
        updateFieldsOnObject(fields, existingProductStock, Brand.class);
        return updateProductStock(mapper.mapFromDomainToDto(existingProductStock));
    }
}
