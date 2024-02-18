package com.shopallday.storage.domain.usecases.products;

import com.shopallday.storage.domain.models.Product;
import com.shopallday.storage.domain.repository.products.ProductsRepository;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.usecases.UseCaseNoReturnVal;

import java.util.List;

public class CreateProductsUseCase implements UseCaseNoReturnVal<List<Product>> {

    private final ProductsRepository productsRepository;
    private final RepositoryManager repositoryManager;

    public CreateProductsUseCase(
            ProductsRepository productsRepository,
            RepositoryManager repositoryManager) {
        this.productsRepository = productsRepository;
        this.repositoryManager = repositoryManager;
    }

    public void execute(List<Product> productList) {
        productsRepository.createProducts(productList, repositoryManager);
    }
}
