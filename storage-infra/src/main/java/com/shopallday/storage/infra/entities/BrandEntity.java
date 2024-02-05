package com.shopallday.storage.infra.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "brands", uniqueConstraints = {
        @UniqueConstraint(name = "brands_name_unique", columnNames = "brand_name")
})
public class BrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brand_seq_generator")
    @SequenceGenerator(name = "brand_seq_generator", sequenceName = "brand_seq", allocationSize = 1, initialValue = 600)
    @Column(name = "brand_id", nullable = false, updatable = false)
    private Long brandId;

    @Column(name = "brand_name", nullable = false, unique = true)
    private String brandName;

}

