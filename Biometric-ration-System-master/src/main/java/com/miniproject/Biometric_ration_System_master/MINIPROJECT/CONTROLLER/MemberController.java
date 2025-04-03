package com.miniproject.Biometric_ration_System_master.MINIPROJECT.CONTROLLER;

import com.miniproject.Biometric_ration_System_master.MINIPROJECT.ENTITIES.Member;
import com.miniproject.Biometric_ration_System_master.MINIPROJECT.SERVICES.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // GET all members
    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = memberService.getAllMembers();
        if (members.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    // GET member by ID
    @GetMapping("/{memberId}")
    public ResponseEntity<Member> getMemberById(@PathVariable int memberId) {
        Optional<Member> member = memberService.getMemberById(memberId);
        return member.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // GET member by Aadhar number
    @GetMapping("/aadhar/{aadharNumber}")
    public ResponseEntity<Member> getMemberByAadhar(@PathVariable String aadharNumber) {
        Optional<Member> member = memberService.getMemberByAadhar(aadharNumber);
        return member.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // GET member by fingerprint data
    @GetMapping("/fingerprint/data/{fingerprintData}")
    public ResponseEntity<Member> getMemberByFingerprintData(@PathVariable String fingerprintData) {
        Optional<Member> member = memberService.getMemberByFingerprintData(fingerprintData);
        return member.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // GET member by fingerprint code
    @GetMapping("/fingerprint/code/{fingerprintCode}")
    public ResponseEntity<Member> getMemberByFingerprintCode(@PathVariable String fingerprintCode) {
        Optional<Member> member = memberService.getMemberByFingerprintCode(fingerprintCode);
        return member.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST create a new member
    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        try {
            if (member.getAadharNumber() == null || member.getAadharNumber().length() != 12) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Validate Aadhar
            }
            Member savedMember = memberService.createMember(member);
            return new ResponseEntity<>(savedMember, HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Error saving member: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PUT update an existing member
    @PutMapping("/{memberId}")
    public ResponseEntity<Member> updateMember(@PathVariable int memberId, @RequestBody Member memberDetails) {
        Optional<Member> existingMember = memberService.getMemberById(memberId);
        if (existingMember.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            Member member = existingMember.get();
            member.setFullName(memberDetails.getFullName());
            member.setAadharNumber(memberDetails.getAadharNumber());
            member.setContactNumber(memberDetails.getContactNumber());
            member.setFingerprintData(memberDetails.getFingerprintData());
            member.setFingerprintCode(memberDetails.getFingerprintCode());
            member.setHouseholds1(memberDetails.getHouseholds1());
            Member updatedMember = memberService.updateMember(member);
            return new ResponseEntity<>(updatedMember, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error updating member: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE a member
    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable int memberId) {
        Optional<Member> member = memberService.getMemberById(memberId);
        if (member.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            memberService.deleteMember(memberId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.err.println("Error deleting member: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}