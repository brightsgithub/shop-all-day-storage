package com.shopallday.storage.infra.repository.orders;

import com.shopallday.storage.domain.models.OrderLine;
import com.shopallday.storage.domain.repository.OrderLinesRepository;
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
    default void createOrderLine(final List<OrderLine> orderLines, RepositoryManager repositoryManager) {
        final EntityManager entityManager = (EntityManager) repositoryManager.getManager();
        final List<OrderLineEntity> orderLineEntities = mapper.mapToEntity(orderLines);
        Merge.mergeOrderLines(entityManager, orderLineEntities);
        saveAll(orderLineEntities);
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
    default void updateOrderLine(final OrderLine orderLine, RepositoryManager repositoryManager) {
        createOrderLine(List.of(orderLine),repositoryManager);
    }

    @Override
    default void deleteOrderLine(final OrderLine orderLine) {
        delete(mapper.mapToEntity(orderLine));
    }
}
