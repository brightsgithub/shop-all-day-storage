package com.shopallday.storage.infra.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products", uniqueConstraints = {
        @UniqueConstraint(name = "PRODUCTS_SHORT_TITLE_UNIQUE", columnNames = "short_title")
})
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq_generator")
    @SequenceGenerator(name = "product_seq_generator", sequenceName = "product_seq", allocationSize = 1, initialValue = 17)
    @Column(name = "product_id", nullable = false, updatable = false)
    private Long productId;

    @ManyToOne(cascade = {CascadeType.PERSIST}) // Cascade. If this is a new Product with a new ProductTypeEntity, save both or update both.
    @JoinColumn(name = "product_type_id", nullable = false)
    private ProductTypeEntity productTypeEntity;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "brand_id", nullable = false)
    private BrandEntity brandEntity;

    @Column(name = "short_title", nullable = false, unique = true)
    private String shortTitle;

    @Column(name = "long_title")
    private String longTitle;

    @Column(name = "short_description", nullable = false)
    @Lob
    private String shortDescription;

    @Column(name = "long_description")
    @Lob
    private String longDescription;
}
