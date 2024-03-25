package com.shopallday.storage.domain.usecases.producttype;

import com.shopallday.storage.domain.exceptions.BusinessErrorCodes;
import com.shopallday.storage.domain.exceptions.crud.CreateException;
import com.shopallday.storage.domain.models.ProductType;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.repository.products.ProductTypeRepository;
import com.shopallday.storage.domain.usecases.UseCase;

public class CreateSingleProductTypeUseCase implements UseCase<ProductType, ProductType> {

    private final ProductTypeRepository productTypeRepository;
    private final RepositoryManager repositoryManager;

    public CreateSingleProductTypeUseCase(ProductTypeRepository productTypeRepository, RepositoryManager repositoryManager) {
        this.productTypeRepository = productTypeRepository;
        this.repositoryManager = repositoryManager;
    }

    @Override
    public ProductType execute(ProductType param) throws CreateException {
        try {
            return productTypeRepository.createProductType(param, repositoryManager);
        } catch (Exception e) {
            throw new CreateException("Could not create ProductType", BusinessErrorCodes.PRODUCT_TYPE_COULD_NOT_BE_CREATED);
        }
    }
}
