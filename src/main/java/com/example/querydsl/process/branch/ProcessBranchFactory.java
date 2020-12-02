package com.example.querydsl.process.branch;

import com.example.querydsl.process.branch.impl.StaffProcessBranch;
import com.example.querydsl.process.branch.impl.StoreProcessBranch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.querydsl.constant.BranchConstant.STAFF_BRANCH;
import static com.example.querydsl.constant.BranchConstant.STORE_BRANCH;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessBranchFactory {

    private final StoreProcessBranch storeProcessBranch;
    private final StaffProcessBranch staffProcessBranch;

    public ProcessBranch getProcessBranch(String branch) {
        ProcessBranch processBranch = null;
        if(STORE_BRANCH.equals(branch)) {
            processBranch = storeProcessBranch;
        } else if(STAFF_BRANCH.equals(branch)) {
            processBranch = staffProcessBranch;
        }
        return processBranch;
    }
}
