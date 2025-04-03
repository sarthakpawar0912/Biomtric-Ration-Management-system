package com.miniproject.Biometric_ration_System_master.MINIPROJECT.CONTROLLER;

import com.miniproject.Biometric_ration_System_master.MINIPROJECT.ENTITIES.Households;
import com.miniproject.Biometric_ration_System_master.MINIPROJECT.SERVICES.HouseholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/households")
public class HouseholdController {

    @Autowired
    private HouseholdService householdService;

    // GET all households
    @GetMapping
    public ResponseEntity<List<Households>> getAllHouseholds() {
        List<Households> households = householdService.getAllHouseholds();
        if (households.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Return 204 if no households exist
        }
        return new ResponseEntity<>(households, HttpStatus.OK); // Return 200 with list
    }

    // GET household by ID
    @GetMapping("/{householdId}")
    public ResponseEntity<Households> getHouseholdById(@PathVariable int householdId) {
        Optional<Households> household = householdService.findById(householdId);
        return household.map(value -> new ResponseEntity<>(value, HttpStatus.OK)) // Return 200 if found
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Return 404 if not found
    }

    // POST create new household
    @PostMapping
    public ResponseEntity<Households> createHousehold(@RequestBody Households household) {
        try {
            // Validate input (e.g., familyHead should not be null or empty)
            if (household.getFamilyHead() == null || household.getFamilyHead().trim().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Return 400 for invalid input
            }
            Households savedHousehold = householdService.saveHousehold(household);
            return new ResponseEntity<>(savedHousehold, HttpStatus.CREATED); // Return 201 on success
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 on error
        }
    }

    // PUT update existing household
    @PutMapping("/{householdId}")
    public ResponseEntity<Households> updateHousehold(
            @PathVariable int householdId, @RequestBody Households householdDetails) {
        Optional<Households> existingHousehold = householdService.findById(householdId);
        if (existingHousehold.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if household not found
        }

        try {
            Households household = existingHousehold.get();
            household.setFamilyHead(householdDetails.getFamilyHead());
            household.setAllocatedRation(householdDetails.getAllocatedRation());
            household.setRationStatus(householdDetails.isRationStatus());
            // Note: Members list update might need separate handling depending on your logic

            Households updatedHousehold = householdService.saveHousehold(household);
            return new ResponseEntity<>(updatedHousehold, HttpStatus.OK); // Return 200 on success
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 on error
        }
    }

    // DELETE household by ID
    @DeleteMapping("/{householdId}")
    public ResponseEntity<Void> deleteHousehold(@PathVariable int householdId) {
        Optional<Households> household = householdService.findById(householdId);
        if (household.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if not found
        }

        try {
            householdService.deleteHousehold(householdId); // Assuming you add this method in service
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Return 204 on successful deletion
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 on error
        }
    }

    // PATCH update ration status (e.g., after biometric verification)
    @PatchMapping("/{householdId}/ration-status")
    public ResponseEntity<Households> updateRationStatus(
            @PathVariable int householdId, @RequestParam boolean rationStatus) {
        Optional<Households> existingHousehold = householdService.findById(householdId);
        if (existingHousehold.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if not found
        }

        try {
            Households household = existingHousehold.get();
            household.setRationStatus(rationStatus);
            Households updatedHousehold = householdService.saveHousehold(household);
            return new ResponseEntity<>(updatedHousehold, HttpStatus.OK); // Return 200 on success
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 on error
        }
    }
}