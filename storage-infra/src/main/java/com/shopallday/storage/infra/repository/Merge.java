package com.shopallday.storage.infra.repository;

import com.shopallday.storage.infra.entities.*;
import jakarta.persistence.EntityManager;

import java.util.List;

/**
 * There are cases where we Create new Entities that are part of existing entities such as a one-to-many relationship.
 * Consider Products for example, which when creating a new Product, it has a relationship with an existing
 * ProductTypeEntity (which has relationships with other existing entities) and BrandEntity. When creating a new
 * ProductEntity, an existing ProductType and Brand would be attached to the new Product but these will be in a detached
 * state within the context. When attempting to save the object, you will encounter the following error:
 *
 * Caused by: org.hibernate.PersistentObjectException: detached entity passed to persist: com.shopallday.storage.infra.entities.BrandEntity
 *
 * This is because the existing objects are NOT attached to the context and need to be merged to the existing context
 * when saving a new object that also includes existing ones. This is due to the having the CascadeType.PERSIST. When
 * saving a new ProductEntity for example, CascadeType.PERSIST will look at any existing objects and update or create them
 * as part of that transaction. The reason why this merging can be removed from the tests and the tests still work, is
 * because all of the objects are created upfront in the same test method and are part of the same context session,
 * so there is no detached state.
 */
public class Merge {

    public static void mergeProductEntity(EntityManager entityManager, List<ProductEntity> productEntities) {
        for (ProductEntity productEntity : productEntities) {
            mergeProductEntity(entityManager, productEntity);
        }
    }

    public static void mergeProductEntity(EntityManager entityManager, ProductEntity productEntity) {
        BrandEntity brandEntity = entityManager.merge(productEntity.getBrandEntity());

        mergeProductTypeEntity(entityManager, productEntity.getProductTypeEntity());
        ProductTypeEntity productTypeEntity = entityManager.merge(productEntity.getProductTypeEntity());

        // Set productEntity with merged brandEntity, productTypeEntity
        productEntity.setBrandEntity(brandEntity);
        productEntity.setProductTypeEntity(productTypeEntity);
    }

    public static void mergeProductStockEntity(EntityManager entityManager, List<ProductStockEntity> productStockEntities) {
        for (ProductStockEntity productStockEntity : productStockEntities) {
            mergeProductStockEntity(entityManager, productStockEntity);
        }
    }

    public static void mergeProductStockEntity(EntityManager entityManager, ProductStockEntity productStockEntity) {

        mergeProductEntity(entityManager, productStockEntity.getProductEntity());
        ProductEntity mergedProductEntity = entityManager.merge(productStockEntity.getProductEntity());

        // Set productStockEntity with merged productEntity
        productStockEntity.setProductEntity(mergedProductEntity );
    }

    public static void mergeProductTypeEntity(EntityManager entityManager, List<ProductTypeEntity> productTypeEntityList) {
        for (ProductTypeEntity productTypeEntity : productTypeEntityList) {
            mergeProductTypeEntity(entityManager,productTypeEntity);
        }
    }

    public static void mergeProductTypeEntity(
            EntityManager entityManager,
            ProductTypeEntity productTypeEntity
    ) {
        CategoryEntity categoryEntity = entityManager.merge(productTypeEntity.getCategoryEntity());
        // Set productTypeEntity with merged categoryEntity
        productTypeEntity.setCategoryEntity(categoryEntity);
    }

    public static void mergeCustomerShipAddressEntity(
            EntityManager entityManager,
            List<CustomerShippingAddressEntity> shippingAddressEntities
    ){
        for (CustomerShippingAddressEntity shippingAddressEntity : shippingAddressEntities) {
            mergeCustomerShipAddressEntity(entityManager, shippingAddressEntity);
        }
    }

    public static void mergeCustomerShipAddressEntity(
            EntityManager entityManager,
            CustomerShippingAddressEntity shippingAddressEntity
    ) {
        CustomerEntity customerEntity = shippingAddressEntity.getCustomerEntity();
        CustomerEntity mergedCustomerEntity = entityManager.merge(customerEntity);
        shippingAddressEntity.setCustomerEntity(mergedCustomerEntity);
    }

    public static void mergeOrders(EntityManager entityManager, List<OrderEntity> orderEntities) {
        for (OrderEntity orderEntity : orderEntities) {
            mergeOrders(entityManager, orderEntity);
        }
    }

    public static void mergeOrders(EntityManager entityManager, OrderEntity orderEntity) {
        CustomerEntity customerEntity = entityManager.merge(orderEntity.getCustomerEntity());
        OrderStatusTypeEntity orderStatusTypeEntity = entityManager.merge(orderEntity.getOrderStatusTypeEntity());

        orderEntity.setCustomerEntity(customerEntity);
        orderEntity.setOrderStatusTypeEntity(orderStatusTypeEntity);
    }

    public static void mergeOrderLines(EntityManager entityManager, List<OrderLineEntity> orderLineEntities) {
        for (OrderLineEntity orderLineEntity : orderLineEntities) {
            mergeOrderLines(entityManager, orderLineEntity);
        }
    }

    public static void mergeOrderLines(EntityManager entityManager, OrderLineEntity orderLineEntity) {
        mergeOrders(entityManager, orderLineEntity.getOrderEntity());
        mergeProductEntity(entityManager, orderLineEntity.getProductEntity());

        OrderEntity mergedOrderEntity = entityManager.merge(orderLineEntity.getOrderEntity());
        ProductEntity mergedProductEntity = entityManager.merge(orderLineEntity.getProductEntity());

        orderLineEntity.setOrderEntity(mergedOrderEntity);
        orderLineEntity.setProductEntity(mergedProductEntity);
    }

    private static void isManaged(EntityManager entityManager, Object object) {
        System.out.println(object.getClass().getSimpleName() +" "+entityManager.contains(object));
    }
}
