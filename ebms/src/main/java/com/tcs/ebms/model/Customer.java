package com.tcs.ebms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @Column(name = "cid", nullable = false, updatable = false)
    private Long cid; // Changed to Long to accommodate 13 digits

    @Column(name = "username")
    private String username;

    @Column(name = "title")
    private String title;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "mobileNumber")
    private String mobileNumber;

    @Column(name = "role")
    private String role;

    @Column(name = "isActive")
    private boolean isActive;

    @Column(name = "billNumber")
    private String billNumber;

    @Column(name = "password")
    private String password;

}