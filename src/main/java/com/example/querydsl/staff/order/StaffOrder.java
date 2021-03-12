package com.example.querydsl.staff.order;

import com.example.querydsl.order.QuerydslOrder;
import com.querydsl.core.types.dsl.ComparableExpressionBase;

import java.util.Optional;

import static com.example.querydsl.staff.entity.QStaff.staff;

public enum StaffOrder implements QuerydslOrder {
    STAFF_ID("id", staff.id),
    STAFF_NAME("name", staff.name),
    STAFF_AGE("age", staff.age),
    STAFF_LAST_NAME("lastName", staff.lastName);

    private final String name;
    private final ComparableExpressionBase<?> comparableExpressionBase;

    StaffOrder(String name, ComparableExpressionBase<?> comparableExpressionBase) {
        this.name = name;
        this.comparableExpressionBase = comparableExpressionBase;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public ComparableExpressionBase<?> getComparableExpressionBase() {
        return this.comparableExpressionBase;
    }

    public static Optional<ComparableExpressionBase<?>> getComparableExpressionBaseByName(String name) {
        for (StaffOrder value : StaffOrder.values()) {
            if (value.name.equals(name)) {
                return Optional.of(value.comparableExpressionBase);
            }
        }
        return Optional.empty();
    }
}
