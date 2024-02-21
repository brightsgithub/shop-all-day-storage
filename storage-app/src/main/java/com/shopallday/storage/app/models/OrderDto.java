package com.shopallday.storage.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long orderId;
    private Timestamp orderDate;
    private CustomerDto customer;
    private OrderStatusTypeDto orderStatusTypeDto;
}
