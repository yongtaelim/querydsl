package com.example.querydsl.house.service;

import com.example.querydsl.basic.BasicTest;
import com.example.querydsl.house.entity.House;
import com.example.querydsl.house.repository.HouseRepository;
import com.example.querydsl.staff.entity.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HouseServiceTest extends BasicTest {
    @Autowired
    private HouseService houseService;

    @Autowired
    private HouseRepository houseRepository;

    @Test
    @DisplayName("staff 명 조회 N + 1 테스트")
    void findStaffNames_test() {
        //given
        setStaffTestData();

        //when
        List<String> staffNames = houseService.findStaffNames();

        //then
        assertThat(staffNames).isNotNull();
        assertThat(staffNames).hasSize(10);
    }

    @Test
    @DisplayName("staff 명 조회 N + 1 회피 테스트 Fetch Join")
    void findStaffName_FetchJoin_test() {
        //given
        setStaffTestData();

        //when
        List<House> houses = houseRepository.findAllByJoinFetch();

        //then
        assertThat(houses).isNotNull();
        assertThat(houses).hasSize(10);
    }

    @Test
    @DisplayName("staff 명 조회 N + 1 회피 테스트 EntityGraph")
    void findStaffName_EntityGraph_test() {
        //given
        setStaffTestData();

        //when
        List<House> houses = houseRepository.findAllEntityGrapeWithStaff();

        //then
        assertThat(houses).hasSize(10);
    }

    @Test
    @DisplayName("staff 명 조회 N + 1 회피 테스트 (카테시안 곱)")
    void findStaffName_cartesian_product_test() {
        //given
        setStaffTestData2();

        //when
        List<House> allByJoinFetch = houseRepository.findAllByJoinFetch();
        List<House> allEntityGrapeWithStaff = houseRepository.findAllEntityGrapeWithStaff();

        //then
        assertThat(allByJoinFetch).hasSize(20);
        assertThat(allEntityGrapeWithStaff).hasSize(20);
    }

    @Test
    @DisplayName("무분별한 DirtyChecking")
    void badUpdate() {
        //given
        setHouseTestData();

        //when
        houseService.badUpdate();
    }

    @Test
    @DisplayName("일괄 update")
    void updateAll() {
        //given
        final String name = "강남";
        setStaffTestData();


        //when
        Long count = houseRepository.updateAddressByName(name);

        //then
        assertThat(count).isEqualTo(0);
    }

    private void setHouseTestData() {
        List<House> houses = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            House house = House.builder()
                    .name("강남아파트" + i)
                    .build();
            houses.add(house);
        }
        houseRepository.saveAll(houses);
    }

    private void setStaffTestData() {
        List<House> houses = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            House house = House.builder()
                    .name("강남아파트" + i)
                    .build();

            house.addStaff(
                    Staff.builder()
                            .name("임용태" + i)
                            .build()
            );
            houses.add(house);
        }
        houseRepository.saveAll(houses);
    }

    private void setStaffTestData2() {
        List<House> houses = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            House house = House.builder()
                    .name("강남아파트" + i)
                    .build();

            house.addStaff(
                    Staff.builder()
                            .name("임용태"+ i)
                            .build()
            );

            house.addStaff(
                    Staff.builder()
                            .name("임용태님"+ i)
                            .build()
            );

            houses.add(house);
        }

        houseRepository.saveAll(houses);
    }
}