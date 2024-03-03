package com.shopallday.storage.app.controllers.products;

import com.shopallday.storage.app.controllers.CustomErrorResponse;
import com.shopallday.storage.app.mappers.Mapper;
import com.shopallday.storage.app.models.ProductDto;
import com.shopallday.storage.app.services.products.ProductsService;
import com.shopallday.storage.domain.exceptions.product.CreateProductException;
import com.shopallday.storage.domain.exceptions.product.DeleteProductException;
import com.shopallday.storage.domain.exceptions.product.ReadProductException;
import com.shopallday.storage.domain.models.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/products")
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
        } catch (ReadProductException rce) {
            CustomErrorResponse errorResponse = new CustomErrorResponse(rce);
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity deleteProductById(@PathVariable("id") Long id) {
        try {
            productsService.deleteProductById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DeleteProductException rce) {
            CustomErrorResponse errorResponse = new CustomErrorResponse(rce);
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
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
            CustomErrorResponse errorResponse = new CustomErrorResponse(e);
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }
    }
}
