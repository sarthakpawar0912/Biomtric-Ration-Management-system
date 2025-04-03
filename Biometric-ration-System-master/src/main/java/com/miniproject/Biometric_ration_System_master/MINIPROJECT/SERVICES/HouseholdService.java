package com.miniproject.Biometric_ration_System_master.MINIPROJECT.SERVICES;

import com.miniproject.Biometric_ration_System_master.MINIPROJECT.ENTITIES.Households;
import com.miniproject.Biometric_ration_System_master.MINIPROJECT.REPOSITORY.HouseholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class HouseholdService {

    @Autowired
    private HouseholdRepository householdRepository;

    public List<Households> getAllHouseholds() {
        return householdRepository.findAll();
    }

    public Optional<Households> findById(int householdId) {
        return householdRepository.findById(householdId);
    }

    public Households saveHousehold(Households household) {
        return householdRepository.save(household);
    }

    public void deleteHousehold(int householdId) {
        householdRepository.deleteById(householdId);
    }
}