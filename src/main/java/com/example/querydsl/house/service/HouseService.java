package com.example.querydsl.house.service;

import com.example.querydsl.house.entity.House;
import com.example.querydsl.house.repository.HouseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class HouseService {
    private HouseRepository houseRepository;

    public HouseService(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    @Transactional(readOnly = true)
    public List<String> findStaffNames() {
        List<House> houses = houseRepository.findAll();
        return filterStaffNames(houses);
    }

    private List<String> filterStaffNames(List<House> houses) {
        return houses.stream()
                .map(house -> house.getStaffs().get(0).getName())
                .collect(Collectors.toList());
    }
}
