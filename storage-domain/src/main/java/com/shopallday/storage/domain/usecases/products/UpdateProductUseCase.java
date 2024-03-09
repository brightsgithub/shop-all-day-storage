package com.shopallday.storage.domain.usecases.products;

import com.shopallday.storage.domain.exceptions.product.ReadProductException;
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
    public Product execute(Product product) throws UpdateProductException, ReadProductException {
        try {
            final Long productId = product.getProductId();

            if(productId == null) {
                throw new ReadProductException("null");
            }

            if(!productsRepository.isExists(productId)) {
                throw new ReadProductException(productId.toString());
            }

            return productsRepository.updateProduct(product, repositoryManager);
        }
        catch (ReadProductException e) {
            throw e;
        }
        catch (Exception exception) {
            throw new UpdateProductException("Product with id "+ product.getProductId()+" could not be updated");
        }
    }
}
