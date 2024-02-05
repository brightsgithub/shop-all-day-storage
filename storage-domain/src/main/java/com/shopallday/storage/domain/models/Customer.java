package com.shopallday.storage.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Customer {
    private Long customerId;
    private final String firstName;
    private final String lastName;
    private final String address1;
    private final String address2;
    private final String city;
    private final String postCode;
    private final String username;
    private final String password;
    private final String enabled;
    private final String email;
    private final String phoneNumber;
}
