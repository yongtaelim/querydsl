package com.example.querydsl.bag.repository;

import com.example.querydsl.bag.entity.Bag;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.example.querydsl.bag.entity.QBag.bag;
import static com.example.querydsl.staff.entity.QStaff.staff;

@RequiredArgsConstructor
public class BagQueryRepositoryImpl implements BagQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Bag findCrossJoinByStaffName(String name) {
        return queryFactory
                .selectFrom(bag)
                .where(bag.staff.name.eq(name))
                .fetchOne();
    }

    @Override
    public Bag findInnerJoinByStaffName(String name) {
        return queryFactory
                .selectFrom(bag)
                .innerJoin(bag.staff, staff)
                .where(staff.name.eq(name))
                .fetchOne();
    }
}
