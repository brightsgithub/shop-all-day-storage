package com.shopallday.storage.domain.usecases.products;

import com.shopallday.storage.domain.models.ProductType;
import com.shopallday.storage.domain.repository.CategoryRepository;
import com.shopallday.storage.domain.repository.ProductTypeRepository;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.usecases.UseCaseNoReturnVal;

import java.util.List;

public class CreateProductTypeUseCase implements UseCaseNoReturnVal<List<ProductType>> {

    private final ProductTypeRepository productTypeRepository;
    private final RepositoryManager repositoryManager;

    public CreateProductTypeUseCase(ProductTypeRepository productTypeRepository, RepositoryManager repositoryManager) {
        this.productTypeRepository = productTypeRepository;
        this.repositoryManager = repositoryManager;
    }

    public void execute(List<ProductType> productTypes) {
        productTypeRepository.createProductTypes(productTypes, repositoryManager);
    }
}
