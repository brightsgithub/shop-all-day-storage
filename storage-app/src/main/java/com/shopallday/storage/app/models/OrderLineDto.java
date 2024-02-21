package com.shopallday.storage.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineDto {

    private Long orderLinesId;
    private OrderDto orderDto;
    private ProductDto productDto;
    private Integer quantity;
    private String size;
    private String color;
}

