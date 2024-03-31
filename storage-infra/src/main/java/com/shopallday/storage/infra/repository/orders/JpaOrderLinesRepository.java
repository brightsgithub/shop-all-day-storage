package com.shopallday.storage.infra.repository.orders;

import com.shopallday.storage.domain.models.OrderLine;
import com.shopallday.storage.domain.repository.orders.OrderLinesRepository;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.infra.entities.OrderLineEntity;
import com.shopallday.storage.infra.mappers.OrderLineMapper;
import com.shopallday.storage.infra.repository.Merge;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaOrderLinesRepository extends JpaRepository<OrderLineEntity, Long>, OrderLinesRepository {

    OrderLineMapper mapper = OrderLineMapper.INSTANCE;

    @Override
    default List<OrderLine> createOrderLine(final List<OrderLine> orderLines, RepositoryManager repositoryManager) {
        final EntityManager entityManager = (EntityManager) repositoryManager.getManager();
        final List<OrderLineEntity> orderLineEntities = mapper.mapToEntity(orderLines);
        Merge.mergeOrderLines(entityManager, orderLineEntities);
        return mapper.mapToDomain(saveAll(orderLineEntities));
    }
    @Override
    default OrderLine createOrderLine(final OrderLine orderLine, RepositoryManager repositoryManager) {
        final EntityManager entityManager = (EntityManager) repositoryManager.getManager();
        final OrderLineEntity orderLineEntity = mapper.mapToEntity(orderLine);
        Merge.mergeOrderLines(entityManager, orderLineEntity);
        return mapper.mapToDomain(save(orderLineEntity));
    }

    @Override
    default List<OrderLine> getAllOrderLines() {
        return mapper.mapToDomain(findAll());
    }

    @Override
    default List<OrderLine> findOrderLinesById(final List<Long> ids) {
        return mapper.mapToDomain(findAllById(ids));
    }

    @Override
    default OrderLine updateOrderLine(final OrderLine orderLine, RepositoryManager repositoryManager) {
        return createOrderLine(orderLine,repositoryManager);
    }

    @Override
    default void deleteOrderLine(final Long id) {
        deleteById(id);
    }
    @Override
    default boolean isExists(Long id) {
        return existsById(id);
    }
}
