package com.shopallday.storage.infra.repository.orders;

import com.shopallday.storage.domain.models.OrderStatusType;
import com.shopallday.storage.domain.repository.OrderStatusTypeRepository;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.infra.entities.OrderStatusTypeEntity;
import com.shopallday.storage.infra.mappers.OrderStatusTypeMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaOrderStatusTypeRepository extends JpaRepository<OrderStatusTypeEntity, Long>, OrderStatusTypeRepository {

    OrderStatusTypeMapper mapper = OrderStatusTypeMapper.INSTANCE;
    @Override
    default void createOrderStatusType(final List<OrderStatusType> orderStatusTypes, RepositoryManager repositoryManager) {
        saveAll(mapper.mapToEntity(orderStatusTypes));
    }

    @Override
    default List<OrderStatusType> getAllOrderStatusTypes() {
        return mapper.mapToDomain(findAll());
    }

    @Override
    default List<OrderStatusType> findOrderStatusTypeById(final List<Long> ids) {
        return mapper.mapToDomain(findAllById(ids));
    }

    @Override
    default void updateOrderStatusType(final OrderStatusType orderStatusType, RepositoryManager repositoryManager) {
        createOrderStatusType(List.of(orderStatusType), repositoryManager);
    }

    @Override
    default void deleteOrderStatusType(final OrderStatusType orderStatusType) {
        delete(mapper.mapToEntity(orderStatusType));
    }
}
