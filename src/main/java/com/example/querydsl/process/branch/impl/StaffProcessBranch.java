package com.example.querydsl.process.branch.impl;

import com.example.querydsl.process.branch.ProcessBranch;
import com.example.querydsl.staff.entity.Staff;
import com.example.querydsl.staff.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StaffProcessBranch implements ProcessBranch {
    private final StaffRepository staffRepository;

    @Override
    public String process() {
        Optional<Staff> staffOptional = staffRepository.findById(1L);
        if(!staffOptional.isPresent()) {
            return "값이 없다";
        }
        Staff staff = staffOptional.get();
        return staff.getName();
    }
}
