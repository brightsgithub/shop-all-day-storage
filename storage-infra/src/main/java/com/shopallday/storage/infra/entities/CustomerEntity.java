package com.shopallday.storage.infra.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="customer")
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "username_unique", columnNames = "username"),
        @UniqueConstraint(name = "email_unique", columnNames = "email")
})
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq_generator")
    @SequenceGenerator(name = "customer_seq_generator", sequenceName = "customer_seq", allocationSize = 1, initialValue = 10)
    private Long customerId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "address1", nullable = false)
    private String address1;

    @Column(name = "address2")
    private String address2;

    @Column(name = "city")
    private String city;

    @Column(name = "post_code", nullable = false)
    private String postCode;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled")
    private String enabled;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
}
