package com.shopallday.storage.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusType {

    private Long orderStatusTypeId;
    private String status;
}

