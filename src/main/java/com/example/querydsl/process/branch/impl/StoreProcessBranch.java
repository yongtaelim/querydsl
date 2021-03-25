package com.example.querydsl.process.branch.impl;

import com.example.querydsl.process.branch.ProcessBranch;
import com.example.querydsl.store.entity.Store;
import com.example.querydsl.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreProcessBranch implements ProcessBranch {

    private final StoreRepository storeRepository;

    @Override
    public String process() {
        Optional<Store> storeOptional = storeRepository.findById(1L);
        if (!storeOptional.isPresent()) {
            return "값이 없다";
        }
        Store store = storeOptional.get();
        return store.getName();
    }
}
