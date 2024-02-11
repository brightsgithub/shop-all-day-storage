package com.shopallday.storage.infra.repository;

import com.shopallday.storage.domain.models.ProductStock;
import com.shopallday.storage.domain.repository.ProductStockRepository;
import com.shopallday.storage.infra.entities.ProductStockEntity;
import com.shopallday.storage.infra.mappers.ProductStockMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JPAProductStockRepository extends JpaRepository<ProductStockEntity, Long>, ProductStockRepository {

    ProductStockMapper mapper = ProductStockMapper.INSTANCE;
    @Override
    default void createProductStock(List<ProductStock> productStocks) {
        saveAll(mapper.mapToEntity(productStocks));
    }

    @Override
    default void createProductStock(ProductStock productStock) {
        save(mapper.mapToEntity(productStock));
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
    default void updateProductStock(ProductStock productStock) {
        createProductStock(productStock);
    }

    @Override
    default void deleteProductStock(ProductStock productStock) {
        delete(mapper.mapToEntity(productStock));
    }
}
