package com.shopallday.storage.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class CustomerOrderDetailDto {

    private Long customerId;
    private Long orderId;
    private Long productStockId;
    private String categoryName;
    private String brandName;
    private String productTypeName;
    private String shortTitle;
    private Timestamp timestamp;
    private Integer quantity;
    private String size;
    private String color;
    private Float price;
    private String status;
}
