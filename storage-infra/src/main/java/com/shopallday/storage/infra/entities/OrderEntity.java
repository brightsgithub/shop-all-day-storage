package com.shopallday.storage.infra.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "orders")
@SequenceGenerator(name = "order_seq", sequenceName = "order_seq", initialValue = 30, allocationSize = 1)
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_date")
    private Timestamp orderDate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customerEntity;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "order_status_type_id", nullable = false)
    private OrderStatusTypeEntity orderStatusTypeEntity;
}

