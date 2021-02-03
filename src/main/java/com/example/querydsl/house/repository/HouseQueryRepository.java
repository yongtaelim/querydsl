package com.example.querydsl.house.repository;

import com.example.querydsl.house.entity.House;

import java.util.List;

public interface HouseQueryRepository {
    List<House> findByHouseGroupById();

    List<House> findByHouseGroupByName();

    List<House> findByHouseGroupByNameNotFileSort();

    Long updateAddressByName(String name);
}
