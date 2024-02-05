package com.shopallday.storage.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {
    private Long customerId;
    private String firstName;
    private String lastName;
    private String address1;
    private String address2;
    private String city;
    private String postCode;
    private String username;
    private String password;
    private String enabled;
    private String email;
    private String phoneNumber;
}
