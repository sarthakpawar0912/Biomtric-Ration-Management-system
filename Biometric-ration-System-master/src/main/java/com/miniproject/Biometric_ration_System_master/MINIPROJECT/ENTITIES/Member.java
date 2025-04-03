package com.miniproject.Biometric_ration_System_master.MINIPROJECT.ENTITIES;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
@Table(name = "members1")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberId;

    private String fullName;

    @Column(unique = true, length = 12, nullable = false)
    private String aadharNumber;

    private String contactNumber;

    @Lob
    private String fingerprintData; // Base64-encoded fingerprint data

    private String fingerprintCode; // Unique identifier for fingerprint

    @ManyToOne
    @JoinColumn(name = "householdId") // Foreign key linking to Households
    @JsonBackReference // Prevents circular reference in JSON
    private Households households1;
}