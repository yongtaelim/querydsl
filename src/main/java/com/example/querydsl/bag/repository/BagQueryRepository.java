package com.example.querydsl.bag.repository;

import com.example.querydsl.bag.entity.Bag;

public interface BagQueryRepository {
    Bag findCrossJoinByStaffName(String name);

    Bag findInnerJoinByStaffName(String name);
}
