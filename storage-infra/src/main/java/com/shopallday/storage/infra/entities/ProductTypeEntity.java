package com.shopallday.storage.infra.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_type")
public class ProductTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_type_seq_generator")
    @SequenceGenerator(name = "product_type_seq_generator", sequenceName = "product_type_seq", allocationSize = 1, initialValue = 99)
    @Column(name = "product_type_id", nullable = false, updatable = false)
    private Long productTypeId;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity categoryEntity;

    @Column(name = "product_type_name", nullable = false)
    private String productTypeName;

    // Constructors, getters, setters, and other methods as needed

}

