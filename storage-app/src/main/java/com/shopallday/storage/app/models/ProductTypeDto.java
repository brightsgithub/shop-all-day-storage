package com.shopallday.storage.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductTypeDto {
    private Long productTypeId;
    private CategoryDto categoryDto;
    private String productTypeName;
}
