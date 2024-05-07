package com.shopallday.storage.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private Long productId;
    private ProductType productType;
    private Brand brand;
    private String shortTitle;
    private String longTitle;
    private String shortDescription;
    private String longDescription;
    private String imageUrl;
}
