package com.shopallday.storage.app.controllers.products;

import com.shopallday.storage.app.mappers.Mapper;
import com.shopallday.storage.app.models.ProductDto;
import com.shopallday.storage.app.services.products.ProductsService;
import com.shopallday.storage.domain.models.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
