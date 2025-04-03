package com.miniproject.Biometric_ration_System_master.MINIPROJECT.ENTITIES;

import lombok.Data;

@Data
public class FingerprintData {

    private String fingerprintCode;  // Unique identifier for fingerprint
    private String fingerprintData;  // Base64-encoded fingerprint (stored as a string)
}
