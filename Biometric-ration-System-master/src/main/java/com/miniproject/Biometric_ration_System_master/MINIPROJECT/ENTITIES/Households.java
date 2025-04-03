package com.miniproject.Biometric_ration_System_master.MINIPROJECT.ENTITIES;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@Table(name = "households1")
public class Households {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "householdId")
    private int householdId;

    private String familyHead;
    private double allocatedRation;
    private boolean rationStatus;

    @OneToMany(mappedBy = "households1", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference // Forward reference
    private List<Member> members1 = new ArrayList<>();
}