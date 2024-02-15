package com.shopallday.storage.infra.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CUSTOMER_SHIPPING_ADDRESS")
public class CustomerShippingAddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_shipping_add_seq_generator")
    @SequenceGenerator(name = "customer_shipping_add_seq_generator", sequenceName = "customer_shipping_add_seq", allocationSize = 1, initialValue = 123)
    @Column(name = "shipping_address_id", nullable = false, updatable = false)
    private Long shippingAddressId;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customerEntity;

    @Column(name = "address1", nullable = false)
    private String address1;

    @Column(name = "address2")
    private String address2;

    @Column(name = "city")
    private String city;

    @Column(name = "post_code", nullable = false)
    private String postCode;

}

