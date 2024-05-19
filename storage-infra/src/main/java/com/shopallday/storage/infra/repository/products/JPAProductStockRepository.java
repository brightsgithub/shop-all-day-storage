package com.shopallday.storage.infra.repository.products;

import com.shopallday.storage.domain.models.ProductStock;
import com.shopallday.storage.domain.repository.products.ProductStockRepository;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.infra.entities.*;
import com.shopallday.storage.infra.mappers.ProductStockMapper;
import com.shopallday.storage.infra.repository.Merge;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JPAProductStockRepository extends JpaRepository<ProductStockEntity, Long>, ProductStockRepository {

    ProductStockMapper mapper = ProductStockMapper.INSTANCE;
    @Override
    default List<ProductStock> createProductStock(List<ProductStock> productStocks, RepositoryManager repositoryManager) {
        final EntityManager entityManager = (EntityManager) repositoryManager.getManager();

        final List<ProductStockEntity> productProductStockEntities = mapper.mapToEntity(productStocks);

        for (ProductStockEntity productStockEntity : productProductStockEntities) {
            Merge.mergeProductStockEntity(entityManager, productStockEntity);
        }

        return mapper.mapToDomain(saveAll(productProductStockEntities));
    }

    @Override
    default ProductStock createProductStock(ProductStock productStock, RepositoryManager repositoryManager) {
        final EntityManager entityManager = (EntityManager) repositoryManager.getManager();

        final ProductStockEntity productStockEntity = mapper.mapToEntity(productStock);
        Merge.mergeProductStockEntity(entityManager, productStockEntity);
        return mapper.mapToDomain(save(productStockEntity));
    }

    @Override
    default List<ProductStock> findAllProductStocks() {
        return mapper.mapToDomain(findAll());
    }

    @Override
    default List<ProductStock> findProductStocksById(List<Long> ids) {
        return mapper.mapToDomain(findAllById(ids));
    }

    @Override
    default ProductStock updateProductStock(ProductStock productStock, RepositoryManager repositoryManager) {
        return createProductStock(productStock, repositoryManager);
    }

    @Override
    default void deleteProductStock(Long id) {
        deleteById(id);
    }
    @Override
    default boolean isExists(Long id) {
        return existsById(id);
    }
    @Override
    void deleteAll();
}
