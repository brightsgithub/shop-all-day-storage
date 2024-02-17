package com.shopallday.storage.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLine {

    private Long orderLinesId;
    private Order order;
    private Product product;
    private Integer quantity;
    private String size;
    private String color;
}

