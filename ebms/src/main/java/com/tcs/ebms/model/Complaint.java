package com.tcs.ebms.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "complaints")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long complaintId;

    @NotBlank
    private String complaintType;

    @NotBlank
    private String category;

    @NotBlank
    private String landMark;

    @NotBlank
    private String customerName;

    @NotBlank
    @Size(min = 10, max = 500)
    private String problem;

    @NotNull
    @Positive
    private Long costumerId;

    @NotBlank
    private String address;

    @NotBlank
    @Pattern(regexp = "^[6-9]\\d{9}$")
    private String mobileNumber;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String status = "PENDING"; // PENDING, IN_PROGRESS, RESOLVED

    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}