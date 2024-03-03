package com.shopallday.storage.app.services.products;

import com.shopallday.storage.app.mappers.Mapper;
import com.shopallday.storage.app.models.ProductDto;
import com.shopallday.storage.domain.exceptions.product.CreateProductException;
import com.shopallday.storage.domain.exceptions.product.DeleteProductException;
import com.shopallday.storage.domain.exceptions.product.ReadProductException;
import com.shopallday.storage.domain.exceptions.product.UpdateProductException;
import com.shopallday.storage.domain.models.Product;
import com.shopallday.storage.domain.usecases.products.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService {

    private CreateProductsUseCase createProductsUseCase;
    private CreateSingleProductUseCase createSingleProductUseCase;
    private GetAllProductsUseCase getAllProductsUseCase;
    private GetProductByIdUseCase getProductByIdUseCase;
    private UpdateProductUseCase updateProductUseCase;
    private DeleteProductUseCase deleteProductUseCase;
    @Qualifier("productMapper")
    private Mapper<Product, ProductDto> productMapper;

    public ProductsServiceImpl(CreateProductsUseCase createProductsUseCase,
                               CreateSingleProductUseCase createSingleProductUseCase,
                               GetAllProductsUseCase getAllProductsUseCase,
                               GetProductByIdUseCase getProductByIdUseCase,
                               UpdateProductUseCase updateProductUseCase,
                               DeleteProductUseCase deleteProductUseCase,
                               @Qualifier("productMapper") Mapper<Product, ProductDto> productMapper) {
        this.createProductsUseCase = createProductsUseCase;
        this.createSingleProductUseCase = createSingleProductUseCase;
        this.getAllProductsUseCase = getAllProductsUseCase;
        this.getProductByIdUseCase = getProductByIdUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
        this.productMapper = productMapper;
    }


    @Override
    @Transactional
    public ProductDto createProduct(Product product) throws CreateProductException {
        final Product justCreatedProduct = createSingleProductUseCase.execute(product);
        return productMapper.mapFromDomainToDto(justCreatedProduct);
    }

    @Override
    @Transactional
    public ProductDto getProductById(Long id) throws ReadProductException {
        final Product product = getProductByIdUseCase.execute(id);
        return productMapper.mapFromDomainToDto(product);
    }

    @Override
    @Transactional
    public List<ProductDto> getAllProducts() {
        return productMapper.mapFromDomainToDto(getAllProductsUseCase.execute());
    }

    @Override
    @Transactional
    public void deleteProductById(Long id) throws DeleteProductException {
        deleteProductUseCase.execute(id);
    }

    @Override
    @Transactional
    public ProductDto updateProduct(Product product) throws UpdateProductException {
        final Product updatedProduct = updateProductUseCase.execute(product);
        return productMapper.mapFromDomainToDto(updatedProduct);
    }
}
