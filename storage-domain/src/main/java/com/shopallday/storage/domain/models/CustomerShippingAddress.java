package com.shopallday.storage.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerShippingAddress {
    private Long shippingAddressId;
    private Customer customer;
    private String address1;
    private String address2;
    private String city;
    private String postCode;
}
