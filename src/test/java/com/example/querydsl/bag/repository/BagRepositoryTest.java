package com.example.querydsl.bag.repository;

import com.example.querydsl.bag.entity.Bag;
import com.example.querydsl.basic.BasicTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class BagRepositoryTest extends BasicTest {

    @Autowired
    BagRepository bagRepository;

    @Test
    @DisplayName("묵시적 조인 테스트")
    void crossJoinTest() {
        //given
        final String name = "임용태";

        //when
        Bag bag = bagRepository.findCrossJoinByStaffName(name);

        //then
        assertThat(bag).isNotNull();
    }

    @Test
    @DisplayName("명시적 조인 테스트")
    void innerJoinTest() {
        //given
        final String name = "임용태";

        //when
        Bag bag = bagRepository.findInnerJoinByStaffName(name);

        //then
        assertThat(bag).isNotNull();
    }
}