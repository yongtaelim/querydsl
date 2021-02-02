package com.example.querydsl.house.repository;

import com.example.querydsl.basic.BasicTest;
import com.example.querydsl.house.entity.House;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HouseRepositoryTest extends BasicTest {
    @Autowired
    HouseRepository houseRepository;

    @Test
    @DisplayName("file sort 미발생, group by column에 indexing 되어있음.")
    void findByHouseGroupById_test() {
        //when
        List<House> houses = houseRepository.findByHouseGroupById();

        //then
        assertThat(houses).isNotNull();
    }

    @Test
    @DisplayName("file sort 발생, group by colmun에 indexing 되있지 않음.")
    void findByHouseGroupByName_test() {
        //when
        List<House> houses = houseRepository.findByHouseGroupByName();

        //then
        assertThat(houses).isNotNull();
    }

    @Test
    @DisplayName("file sort 미발생, order by null 처리 ")
    void findByHouseGroupByName_Not_file_sort_test() {
        //when
        List<House> houses = houseRepository.findByHouseGroupByNameNotFileSort();

        //then
        assertThat(houses).isNotNull();
    }
}