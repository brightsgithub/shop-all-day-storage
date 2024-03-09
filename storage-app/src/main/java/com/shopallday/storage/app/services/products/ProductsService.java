package com.shopallday.storage.app.services.products;

import com.shopallday.storage.app.models.ProductDto;
import com.shopallday.storage.domain.exceptions.product.CreateProductException;
import com.shopallday.storage.domain.exceptions.product.DeleteProductException;
import com.shopallday.storage.domain.exceptions.product.ReadProductException;
import com.shopallday.storage.domain.exceptions.product.UpdateProductException;
import com.shopallday.storage.domain.models.Product;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface ProductsService {
    ProductDto createProduct(Product product) throws CreateProductException;
    ProductDto getProductById(Long id) throws ReadProductException;
    List<ProductDto> getAllProducts();
    void deleteProductById(Long id) throws DeleteProductException;
    ProductDto updateProduct(Product product) throws UpdateProductException, ReadProductException;

    @Transactional
    ProductDto partialUpdateProduct(final Long id, final Map<String, Object> fields)
            throws UpdateProductException, ReadProductException;
}
