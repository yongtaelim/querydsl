package com.example.querydsl.house.repository;

import com.example.querydsl.house.entity.House;
import com.example.querydsl.order.OrderByNull;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.querydsl.house.entity.QHouse.house;

@RequiredArgsConstructor
public class HouseQueryRepositoryImpl implements HouseQueryRepository {
    private final JPAQueryFactory queryFactory;


    @Override
    public List<House> findByHouseGroupById() {
        return queryFactory
                .selectFrom(house)
                .groupBy(house.id)
                .fetch();
    }

    @Override
    public List<House> findByHouseGroupByName() {
        return queryFactory
                .selectFrom(house)
                .groupBy(house.name)
                .fetch();
    }

    @Override
    public List<House> findByHouseGroupByNameNotFileSort() {
        return queryFactory
                .selectFrom(house)
                .groupBy(house.name)
                .orderBy(OrderByNull.DEFAULT)
                .fetch();
    }
}
