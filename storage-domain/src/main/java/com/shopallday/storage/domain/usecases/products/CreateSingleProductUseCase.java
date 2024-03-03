package com.shopallday.storage.domain.usecases.products;

import com.shopallday.storage.domain.exceptions.BusinessErrorCodes;
import com.shopallday.storage.domain.exceptions.product.CreateProductException;
import com.shopallday.storage.domain.models.Product;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.repository.products.ProductsRepository;
import com.shopallday.storage.domain.usecases.UseCase;

public class CreateSingleProductUseCase implements UseCase<Product, Product> {

    private final ProductsRepository productsRepository;
    private final RepositoryManager repositoryManager;

    public CreateSingleProductUseCase(
            ProductsRepository productsRepository,
            RepositoryManager repositoryManager) {
        this.productsRepository = productsRepository;
        this.repositoryManager = repositoryManager;
    }

    public Product execute(Product product) throws CreateProductException{
        try {
            return productsRepository.createProduct(product, repositoryManager);
        } catch (Exception e) {
            throw new CreateProductException("Product "+product+" could not be created",
                    BusinessErrorCodes.PRODUCT_COULD_NOT_BE_CREATED);
        }
    }
}