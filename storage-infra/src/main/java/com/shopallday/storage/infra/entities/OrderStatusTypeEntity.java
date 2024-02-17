package com.shopallday.storage.infra.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "order_status_types",uniqueConstraints = {
        @UniqueConstraint(name = "ORDER_STATUS_NAME_UNIQUE", columnNames = "status") // status must be unique
})
@SequenceGenerator(name = "order_status_types_seq", sequenceName = "order_status_types_seq", initialValue = 222, allocationSize = 1)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_status_types_seq")
    @Column(name = "order_status_type_id")
    private Long orderStatusTypeId;

    @Column(name = "status")
    private String status;
}

