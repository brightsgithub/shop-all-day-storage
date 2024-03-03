package com.shopallday.storage.domain.usecases.products;

import com.shopallday.storage.domain.exceptions.product.UpdateProductException;
import com.shopallday.storage.domain.models.Product;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.repository.products.ProductsRepository;
import com.shopallday.storage.domain.usecases.UseCase;

public class UpdateProductUseCase implements UseCase<Product, Product> {

    private ProductsRepository productsRepository;
    private final RepositoryManager repositoryManager;

    public UpdateProductUseCase(
            ProductsRepository productsRepository,
            RepositoryManager repositoryManager) {
        this.productsRepository = productsRepository;
        this.repositoryManager = repositoryManager;
    }

    @Override
    public Product execute(Product product) throws UpdateProductException {
        try {
            return productsRepository.updateProduct(product, repositoryManager);
        }
        catch (Exception exception) {
            throw new UpdateProductException("Product with id "+ product.getProductId()+" could not be updated");
        }
    }
}
