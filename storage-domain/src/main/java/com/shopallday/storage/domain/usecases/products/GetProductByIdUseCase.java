package com.shopallday.storage.domain.usecases.products;

import com.shopallday.storage.domain.exceptions.product.ReadProductException;
import com.shopallday.storage.domain.models.Product;
import com.shopallday.storage.domain.repository.products.ProductsRepository;
import com.shopallday.storage.domain.usecases.UseCase;

import java.util.List;

public class GetProductByIdUseCase implements UseCase<Product, Long> {

    private final ProductsRepository productsRepository;

    public GetProductByIdUseCase(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public Product execute(Long id) throws ReadProductException {
        final List<Product> productList = productsRepository.findProductsByIds(List.of(id));
        if (productList.isEmpty()) {
            throw new ReadProductException(id.toString());
        }
        return productList.get(0);
    }
}