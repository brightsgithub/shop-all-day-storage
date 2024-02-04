package com.shopallday.storage.infra.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_stock")
public class ProductStock {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_stock_seq_generator")
    @SequenceGenerator(name = "product_stock_seq_generator", sequenceName = "product_stock_seq", allocationSize = 1, initialValue = 300)
    @Column(name = "product_stock_id", nullable = false, updatable = false)
    private Long productStockId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "size")
    private String size;

    @Column(name = "color")
    private String color;

    @Column(name = "price", nullable = false)
    private Float price;
}

