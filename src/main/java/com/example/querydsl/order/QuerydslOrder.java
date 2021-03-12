package com.example.querydsl.order;

import com.querydsl.core.types.dsl.ComparableExpressionBase;

public interface QuerydslOrder {
    String getName();

    ComparableExpressionBase<?> getComparableExpressionBase();
}
