package com.shopallday.storage.domain.usecases.producttype;

import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.exceptions.crud.UpdateException;
import com.shopallday.storage.domain.models.ProductType;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.repository.products.ProductTypeRepository;
import com.shopallday.storage.domain.usecases.UseCase;

import static com.shopallday.storage.domain.exceptions.BusinessErrorCodes.PRODUCT_COULD_NOT_BE_UPDATED;
import static com.shopallday.storage.domain.exceptions.BusinessErrorCodes.PRODUCT_TYPE_NOT_FOUND;

public class UpdateProductTypeUseCase implements UseCase<ProductType, ProductType> {

    private final ProductTypeRepository repository;
    private final RepositoryManager repositoryManager;

    public UpdateProductTypeUseCase(
            ProductTypeRepository repository,
            RepositoryManager repositoryManager) {
        this.repository = repository;
        this.repositoryManager = repositoryManager;
    }

    @Override
    public ProductType execute(ProductType productType) throws ReadException, UpdateException {
        try {
            final Long id = productType.getProductTypeId();
            if (id == null || !repository.isExists(id)) {
                throw new ReadException("Cannot find ProductType with id "+id, PRODUCT_TYPE_NOT_FOUND);
            }

            return repository.updateProductType(productType, repositoryManager);
        } catch (ReadException e) {
            throw e;
        }
        catch (Exception e) {
            throw new UpdateException("ProductType with id "+productType.getProductTypeId()+" could not be updated",
                    PRODUCT_COULD_NOT_BE_UPDATED);
        }
    }
}
