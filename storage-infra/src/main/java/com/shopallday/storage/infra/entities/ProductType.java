package com.shopallday.storage.infra.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_type")
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_type_seq_generator")
    @SequenceGenerator(name = "product_type_seq_generator", sequenceName = "product_type_seq", allocationSize = 1, initialValue = 99)
    @Column(name = "product_type_id", nullable = false, updatable = false)
    private Long productTypeId;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "product_type_name", nullable = false)
    private String productTypeName;

    // Constructors, getters, setters, and other methods as needed

}

