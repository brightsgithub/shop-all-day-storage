package com.shopallday.storage.infra.repository.orders;

import com.shopallday.storage.domain.models.Order;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.repository.orders.OrdersRepository;
import com.shopallday.storage.infra.entities.OrderEntity;
import com.shopallday.storage.infra.mappers.OrderMapper;
import com.shopallday.storage.infra.repository.Merge;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaOrdersRepository extends JpaRepository<OrderEntity, Long>, OrdersRepository {

    OrderMapper mapper = OrderMapper.INSTANCE;
    @Override
    default List<Order> createOrder(final List<Order> orders, RepositoryManager repositoryManager) {
        final EntityManager entityManager = (EntityManager) repositoryManager.getManager();
        final List<OrderEntity> orderEntities = mapper.mapToEntity(orders);
        Merge.mergeOrders(entityManager, orderEntities);
        return mapper.mapToDomain(saveAll(orderEntities));
    }
    @Override
    default Order createOrder(final Order order, RepositoryManager repositoryManager) {
        final EntityManager entityManager = (EntityManager) repositoryManager.getManager();
        final OrderEntity orderEntity = mapper.mapToEntity(order);
        Merge.mergeOrders(entityManager, orderEntity);
        return mapper.mapToDomain(save(orderEntity));
    }
    @Override
    default List<Order> getAllOrders() {
        return mapper.mapToDomain(findAll());
    }

    @Override
    default List<Order> findOrdersById(final List<Long> ids) {
        return mapper.mapToDomain(findAllById(ids));
    }

    @Override
    default Order updateOrder(final Order order, RepositoryManager repositoryManager) {
        return createOrder(order, repositoryManager);
    }
    @Override
    default void deleteOrder(final Long id) {
        deleteOrderLinesByOrderId(id);
        deleteById(id);
    }

    @Modifying // needed since this is not a select statement
    @Query("DELETE FROM OrderLineEntity ol WHERE ol.orderEntity.orderId = :orderId")
    void deleteOrderLinesByOrderId(@Param("orderId") Long orderId);

    @Override
    default boolean isExists(Long id) {
        return existsById(id);
    }
}
