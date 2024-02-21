package com.shopallday.storage.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDto {
    private Long productId;
    private ProductTypeDto productTypeDto;
    private BrandDto brandDto;
    private String shortTitle;
    private String longTitle;
    private String shortDescription;
    private String longDescription;
}
