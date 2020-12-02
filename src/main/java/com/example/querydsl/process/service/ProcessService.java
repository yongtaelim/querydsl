package com.example.querydsl.process.service;

import com.example.querydsl.process.branch.ProcessBranch;
import com.example.querydsl.process.branch.ProcessBranchFactory;
import com.example.querydsl.staff.entity.Staff;
import com.example.querydsl.staff.repository.StaffRepository;
import com.example.querydsl.store.entity.Store;
import com.example.querydsl.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.querydsl.constant.BranchConstant.STAFF_BRANCH;
import static com.example.querydsl.constant.BranchConstant.STORE_BRANCH;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessService {

    private final StoreRepository storeRepository;
    private final StaffRepository staffRepository;

    private final ProcessBranchFactory processBranchFactory;


    public String if문_탈출못했네(String branch) {
        if(STORE_BRANCH.equals(branch)) {
            Optional<Store> storeOptional = storeRepository.findById(1L);
            if(!storeOptional.isPresent()) {
                return "값이 없다";
            }
            Store store = storeOptional.get();
            return store.getName();
        } else if(STAFF_BRANCH.equals(branch)) {
            Optional<Staff> staffOptional = staffRepository.findById(1L);
            if(!staffOptional.isPresent()) {
                return "값이 없다";
            }
            Staff staff = staffOptional.get();
            return staff.getName();
        }
        return "분기 못탔다";
    }

    public String if문_탈출성공(String branch) {
        ProcessBranch processBranch = processBranchFactory.getProcessBranch(branch);
        return processBranch.process();
    }

}
