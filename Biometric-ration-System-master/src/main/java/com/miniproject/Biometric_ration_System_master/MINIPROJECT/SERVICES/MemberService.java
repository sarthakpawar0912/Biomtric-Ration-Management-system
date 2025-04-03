package com.miniproject.Biometric_ration_System_master.MINIPROJECT.SERVICES;

import com.miniproject.Biometric_ration_System_master.MINIPROJECT.ENTITIES.Member;
import com.miniproject.Biometric_ration_System_master.MINIPROJECT.REPOSITORY.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    // Get all members
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    // Get member by ID
    public Optional<Member> getMemberById(int memberId) {
        return memberRepository.findById(memberId);
    }

    // Get member by Aadhar number
    public Optional<Member> getMemberByAadhar(String aadharNumber) {
        return memberRepository.findByAadharNumber(aadharNumber);
    }

    // Get member by fingerprint data
    public Optional<Member> getMemberByFingerprintData(String fingerprintData) {
        return memberRepository.findByFingerprintData(fingerprintData);
    }

    // Get member by fingerprint code
    public Optional<Member> getMemberByFingerprintCode(String fingerprintCode) {
        return memberRepository.findByFingerprintCode(fingerprintCode);
    }

    // Create a new member
    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    // Update an existing member
    public Member updateMember(Member member) {
        return memberRepository.save(member);
    }

    // Delete a member by ID
    public void deleteMember(int memberId) {
        memberRepository.deleteById(memberId);
    }
}