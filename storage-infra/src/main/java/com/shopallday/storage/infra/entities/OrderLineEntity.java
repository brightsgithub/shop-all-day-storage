package com.shopallday.storage.infra.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_lines")
@SequenceGenerator(name = "order_line_seq", sequenceName = "order_line_seq", initialValue = 44, allocationSize = 1)
public class OrderLineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_line_seq")
    @Column(name = "order_lines_id")
    private Long orderLinesId;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "order_id", nullable = false, updatable = false)
    private OrderEntity orderEntity;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity productEntity;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "size")
    private String size;

    @Column(name = "color")
    private String color;
}
