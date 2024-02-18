package com.shopallday.storage.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class CustomerOrderDetail {

    private Long customerId;
    private Long orderId;
    private String shortTitle;
    private Timestamp timestamp;
    private Integer quantity;
    private String size;
    private String color;
    private Float price;
    private String status;
}
