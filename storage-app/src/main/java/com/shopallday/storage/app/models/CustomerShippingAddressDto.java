package com.shopallday.storage.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerShippingAddressDto {
    private Long shippingAddressId;
    private CustomerDto customerDto;
    private String address1;
    private String address2;
    private String city;
    private String postCode;
}
