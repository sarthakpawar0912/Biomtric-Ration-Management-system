package com.miniproject.Biometric_ration_System_master.MINIPROJECT.SERVICES;

import com.miniproject.Biometric_ration_System_master.MINIPROJECT.ENTITIES.Households;
import com.miniproject.Biometric_ration_System_master.MINIPROJECT.ENTITIES.Member;
import com.miniproject.Biometric_ration_System_master.MINIPROJECT.REPOSITORY.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FingerprintService {

    private final MemberRepository memberRepository;

    public FingerprintService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // ✅ Match Fingerprint using Aadhar Number
    public Optional<Households> matchFingerprint(String fingerprintCode) {
        Optional<Member> member = memberRepository.findByFingerprintCode(fingerprintCode);
        return member.map(Member::getHouseholds1);
    }


    // ✅ Store Aadhar Number as Fingerprint Identifier
    public void storeFingerprint(String fingerprintCode, String fingerprintData) {
        Optional<Member> memberOptional = memberRepository.findByFingerprintCode(fingerprintCode);
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            member.setFingerprintData(fingerprintData);
            memberRepository.save(member);
        } else {
            throw new RuntimeException("Member not found with Fingerprint ID: " + fingerprintCode);
        }
    }
}