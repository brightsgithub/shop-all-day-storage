package com.shopallday.storage.app.controllers.products;

import com.shopallday.storage.app.controllers.BaseController;
import com.shopallday.storage.app.mappers.Mapper;
import com.shopallday.storage.app.models.ProductDto;
import com.shopallday.storage.app.services.products.ProductsService;
import com.shopallday.storage.domain.exceptions.product.CreateProductException;
import com.shopallday.storage.domain.exceptions.product.DeleteProductException;
import com.shopallday.storage.domain.exceptions.product.ReadProductException;
import com.shopallday.storage.domain.exceptions.product.UpdateProductException;
import com.shopallday.storage.domain.models.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "storage/v1/products")
@Tag(name = "Products", description = "Endpoints for managing products")
public class ProductsController extends BaseController {

    private final ProductsService productsService;
    private final Mapper<Product, ProductDto> productMapper;

    public ProductsController(
            ProductsService productsService,
            @Qualifier("productMapper") Mapper<Product, ProductDto> productMapper) {
        this.productsService = productsService;
        this.productMapper = productMapper;
    }

    @Operation(summary = "Get all products")
    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productsService.getAllProducts();
    }

    @Operation(summary = "Get a product by ID")
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

    @Operation(summary = "Delete a product by ID")
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

    @Operation(summary = "Create a new product")
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

    @Operation(summary = "Update a product by ID")
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

    @Operation(summary = "Partially update a product by ID")
    @PatchMapping(path = "{id}")
    public ResponseEntity partiallyUpdateProduct(
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
}