package com.miniproject.Biometric_ration_System_master.MINIPROJECT.CONTROLLER;

import com.miniproject.Biometric_ration_System_master.MINIPROJECT.ENTITIES.FingerprintData;
import com.miniproject.Biometric_ration_System_master.MINIPROJECT.ENTITIES.Households;
import com.miniproject.Biometric_ration_System_master.MINIPROJECT.SERVICES.FingerprintService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/fingerprint")
public class FingerprintController {

    private final FingerprintService fingerprintService;

    public FingerprintController(FingerprintService fingerprintService) {
        this.fingerprintService = fingerprintService;
    }

    // ✅ Match Fingerprint API
    @PostMapping("/match")
    public Optional<Households> matchFingerprint(@RequestBody FingerprintData data) {
        if (data == null || data.getFingerprintCode() == null) {
            return Optional.empty();  // Avoid null pointer exceptions
        }
        return fingerprintService.matchFingerprint(data.getFingerprintCode());
    }

    // ✅ Store Fingerprint API (WITHOUT Decoding)
    @PostMapping("/store")
    public ResponseEntity<String> storeFingerprint(@RequestBody FingerprintData data) {
        try {
            if (data == null || data.getFingerprintCode() == null || data.getFingerprintData() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fingerprint data is missing");
            }

            // ✅ Store the fingerprint as a Base64 string (no decoding needed)
            fingerprintService.storeFingerprint(data.getFingerprintCode(), data.getFingerprintData());

            return ResponseEntity.ok("Fingerprint stored successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error storing fingerprint: " + e.getMessage());
        }
    }
}

