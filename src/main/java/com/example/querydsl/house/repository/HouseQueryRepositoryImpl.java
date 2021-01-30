package com.example.querydsl.house.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HouseQueryRepositoryImpl implements HouseQueryRepository {
    private final JPAQueryFactory queryFactory;
}
