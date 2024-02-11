package com.shopallday.storage.domain.usecases;

import com.shopallday.storage.domain.models.Product;
import com.shopallday.storage.domain.repository.ProductsRepository;

import java.util.List;

public class GetAllProductsUseCase implements UseCaseNoParams<List<Product>>{

    private final ProductsRepository productsRepository;

    public GetAllProductsUseCase(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Product> execute() {
        return productsRepository.findAllProducts();
    }
}
