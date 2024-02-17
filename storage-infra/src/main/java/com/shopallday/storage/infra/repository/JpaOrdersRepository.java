package com.shopallday.storage.infra.repository;

import com.shopallday.storage.domain.models.Order;
import com.shopallday.storage.domain.repository.OrdersRepository;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.infra.entities.OrderEntity;
import com.shopallday.storage.infra.mappers.OrderMapper;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaOrdersRepository extends JpaRepository<OrderEntity, Long>, OrdersRepository {

    OrderMapper mapper = OrderMapper.INSTANCE;
    default void createOrder(final List<Order> orders, RepositoryManager repositoryManager) {
        final EntityManager entityManager = (EntityManager) repositoryManager.getManager();
        final List<OrderEntity> orderEntities = mapper.mapToEntity(orders);
        Merge.mergeOrders(entityManager, orderEntities);
        saveAll(orderEntities);
    }
    default List<Order> getAllOrders() {
        return mapper.mapToDomain(findAll());
    }

    default List<Order> findOrdersById(final List<Long> ids) {
        return mapper.mapToDomain(findAllById(ids));
    }

    default void updateOrder(final Order order, RepositoryManager repositoryManager) {
        createOrder(List.of(order), repositoryManager);
    }

    default void deleteOrder(final Order order) {
        delete(mapper.mapToEntity(order));
    }
}
