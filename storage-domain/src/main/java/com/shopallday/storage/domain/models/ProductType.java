package com.shopallday.storage.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductType {
    private Long productTypeId;
    private Category category;
    private String productTypeName;
}
