package com.shopallday.storage.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class CategoryDto {
    private Long categoryId;
    private String categoryName;
}
