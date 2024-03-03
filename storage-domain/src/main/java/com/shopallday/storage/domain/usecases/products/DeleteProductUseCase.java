package com.shopallday.storage.domain.usecases.products;

import com.shopallday.storage.domain.exceptions.product.DeleteProductException;
import com.shopallday.storage.domain.repository.products.ProductsRepository;
import com.shopallday.storage.domain.usecases.UseCaseNoReturnVal;

public class DeleteProductUseCase implements UseCaseNoReturnVal<Long> {

    private ProductsRepository productsRepository;

    public DeleteProductUseCase(
            ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public void execute(Long id) throws DeleteProductException {
        try {
            productsRepository.deleteProductById(id);
        }
        catch (Exception exception) {
            throw new DeleteProductException("Product with id "+ id +" could not be deleted");
        }
    }
}
