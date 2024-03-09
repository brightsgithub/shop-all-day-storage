package com.shopallday.storage.app.controllers.products;

import com.shopallday.storage.app.controllers.CustomErrorResponse;
import com.shopallday.storage.app.mappers.Mapper;
import com.shopallday.storage.app.models.ProductDto;
import com.shopallday.storage.app.services.products.ProductsService;
import com.shopallday.storage.domain.exceptions.BusinessException;
import com.shopallday.storage.domain.exceptions.product.CreateProductException;
import com.shopallday.storage.domain.exceptions.product.DeleteProductException;
import com.shopallday.storage.domain.exceptions.product.ReadProductException;
import com.shopallday.storage.domain.exceptions.product.UpdateProductException;
import com.shopallday.storage.domain.models.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "storage/v1/products")
public class ProductsController {

    private final ProductsService productsService;
    private final Mapper<Product, ProductDto> productMapper;

    public ProductsController(
            ProductsService productsService,
            @Qualifier("productMapper") Mapper<Product, ProductDto> productMapper) {
        this.productsService = productsService;
        this.productMapper = productMapper;
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productsService.getAllProducts();
    }

    @GetMapping(path = "{id}")
    public ResponseEntity getProductById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(productsService.getProductById(id), HttpStatus.OK);
        } catch (ReadProductException e) {
            return getErrorResponse(e, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity deleteProductById(@PathVariable("id") Long id) {
        try {
            productsService.deleteProductById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DeleteProductException e) {
            return getErrorResponse(e, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody final ProductDto productDto) {
        final Product product = productMapper.mapFromDtoToDomain(productDto);
        try {
            return new ResponseEntity(productsService.createProduct(product), HttpStatus.CREATED);
        } catch (CreateProductException e) {
            return getErrorResponse(e, HttpStatus.CONFLICT);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping(path = "{id}")
    public ResponseEntity updateProduct(
            @PathVariable("id") Long id,
            @RequestBody final ProductDto productDto
    ) {
        try {
            productDto.setProductId(id);
            final Product product = productMapper.mapFromDtoToDomain(productDto);
            return new ResponseEntity(productsService.updateProduct(product), HttpStatus.OK);
        } catch (ReadProductException | UpdateProductException e) {
            return getErrorResponse(e, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping(path = "{id}")
    public ResponseEntity partialUpdateProduct(
            @PathVariable("id") Long id,
            @RequestBody final Map<String, Object> fields
    ) {
        try {
            return new ResponseEntity(productsService.partialUpdateProduct(id, fields), HttpStatus.OK);
        } catch (ReadProductException | UpdateProductException e) {
             return getErrorResponse(e, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    private ResponseEntity getErrorResponse(BusinessException e, HttpStatus httpStatus) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(e);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }
}


