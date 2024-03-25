package com.shopallday.storage.domain.usecases.producttype;

import com.shopallday.storage.domain.models.ProductType;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.repository.products.ProductTypeRepository;
import com.shopallday.storage.domain.usecases.UseCaseNoParam;

import java.util.List;

public class GetAllProductTypesUseCase implements UseCaseNoParam<List<ProductType>> {

    private final ProductTypeRepository productTypeRepository;
    private final RepositoryManager repositoryManager;

    public GetAllProductTypesUseCase(ProductTypeRepository productTypeRepository, RepositoryManager repositoryManager) {
        this.productTypeRepository = productTypeRepository;
        this.repositoryManager = repositoryManager;
    }

    public List<ProductType> execute() {
        return productTypeRepository.findAllProductTypes(repositoryManager);
    }
}
