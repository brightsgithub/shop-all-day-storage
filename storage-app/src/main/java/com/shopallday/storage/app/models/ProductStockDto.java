package com.shopallday.storage.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductStockDto {
    private Long productStockId;
    private ProductDto productDto;
    private Integer quantity;
    private String size;
    private String color;
    private Float price;
}
