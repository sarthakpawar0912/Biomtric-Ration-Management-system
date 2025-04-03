package com.miniproject.Biometric_ration_System_master.MINIPROJECT.REPOSITORY;

import com.miniproject.Biometric_ration_System_master.MINIPROJECT.ENTITIES.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> { // Use Integer, not Long, to match memberId
    Optional<Member> findByAadharNumber(String aadharNumber);
    Optional<Member> findByFingerprintData(String fingerprintData);
    Optional<Member> findByFingerprintCode(String fingerprintCode);
}