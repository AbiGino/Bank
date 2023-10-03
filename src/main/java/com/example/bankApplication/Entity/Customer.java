package com.example.bankApplication.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="account_number",unique = true)
    Integer account_number;
    @Column(name="name")
    String name;
    @Column(name="email")
    String email;
    @Column(name="password")
    String password;
    @Column(name="phone_number")
    String phone_number;
}
