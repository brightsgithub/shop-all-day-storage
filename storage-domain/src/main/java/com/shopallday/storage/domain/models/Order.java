package com.shopallday.storage.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Long orderId;
    private Timestamp orderDate;
    private Customer customerId;
    private Long orderStatusTypeId;
}
