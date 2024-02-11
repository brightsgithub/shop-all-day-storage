package com.shopallday.storage.domain.usecases;

import com.shopallday.storage.domain.models.Product;
import com.shopallday.storage.domain.repository.ProductsRepository;

import java.util.List;

public class CreateProductsUseCase implements UseCaseNoReturnVal<List<Product>> {

    private final ProductsRepository productsRepository;

    public CreateProductsUseCase(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public void execute(List<Product> productList) {
        productsRepository.createProducts(productList);
    }
}
