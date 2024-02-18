package com.shopallday.storage.domain.usecases.products;

import com.shopallday.storage.domain.models.Product;
import com.shopallday.storage.domain.repository.products.ProductsRepository;
import com.shopallday.storage.domain.usecases.UseCaseNoParam;

import java.util.List;

public class GetAllProductsUseCase implements UseCaseNoParam<List<Product>> {

    private final ProductsRepository productsRepository;

    public GetAllProductsUseCase(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Product> execute() {
        return productsRepository.findAllProducts();
    }
}
