package com.shopallday.storage.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductStock {
    private Long productStockId;
    private Product product;
    private Integer quantity;
    private String size;
    private String color;
    private Float price;
}
