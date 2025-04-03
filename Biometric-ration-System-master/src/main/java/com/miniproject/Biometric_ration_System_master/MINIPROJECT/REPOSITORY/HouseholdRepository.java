package com.miniproject.Biometric_ration_System_master.MINIPROJECT.REPOSITORY;


import com.miniproject.Biometric_ration_System_master.MINIPROJECT.ENTITIES.Households;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

// HouseholdRepository.java
public interface HouseholdRepository extends JpaRepository<Households, Integer> {
    @Query("SELECT h FROM Households h WHERE h.householdId = :householdId")
    Optional<Households> findById(@Param("householdId") int householdId);
}