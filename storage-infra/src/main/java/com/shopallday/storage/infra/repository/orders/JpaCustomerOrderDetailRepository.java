package com.shopallday.storage.infra.repository.orders;

import com.shopallday.storage.domain.models.CustomerOrderDetail;
import com.shopallday.storage.domain.repository.orders.CustomerOrderDetailRepository;
import com.shopallday.storage.infra.entities.OrderLineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaCustomerOrderDetailRepository extends JpaRepository<OrderLineEntity, Long>,CustomerOrderDetailRepository {


    @Override
    @Query("SELECT new com.shopallday.storage.domain.models.CustomerOrderDetail(" +
            "c.customerId, " +
            "o.orderId, " +
            "p.shortTitle, " +
            "o.orderDate, " +
            "ol.quantity, " +
            "ol.size, " +
            "ol.color, " +
            "ps.price, " +
            "ost.status) " +
            "FROM OrderStatusTypeEntity ost, " +
            "OrderEntity o, " +
            "OrderLineEntity ol, " +
            "ProductEntity p, " +
            "CustomerEntity c, " +
            "ProductStockEntity ps " +
            "WHERE ost.orderStatusTypeId = o.orderStatusTypeEntity.orderStatusTypeId " +
            "AND ol.orderEntity.orderId = o.orderId " +
            "AND ol.productEntity.productId = p.productId " +
            "AND o.customerEntity.customerId = c.customerId " +
            "AND ol.productEntity.productId = ps.productEntity.productId " +
            "AND ol.size = ps.size " +
            "AND ol.color = ps.color " +
            "AND c.customerId = :customerId")
    List<CustomerOrderDetail> getOrdersByCustomerId(Long customerId);
}
