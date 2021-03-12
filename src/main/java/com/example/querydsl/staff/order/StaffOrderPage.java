package com.example.querydsl.staff.order;

import com.example.querydsl.order.OrderPage;
import com.example.querydsl.order.SortVo;
import com.querydsl.core.types.OrderSpecifier;

import javax.persistence.EntityManager;
import java.util.List;

public class StaffOrderPage extends OrderPage {

    public StaffOrderPage(EntityManager entityManager, Class returnTypeClass) {
        super(entityManager, returnTypeClass);
    }

    @Override
    public OrderSpecifier<?>[] getOrder(List<SortVo> sorts) {
        for (SortVo sort : sorts) {
            StaffOrder.getComparableExpressionBaseByName(sort.getKey()).ifPresent(
                    comparableExpressionBase -> super.addOrderSpecifier(sort, comparableExpressionBase)
            );
        }

        return this.orderSpecifiers.toArray(new OrderSpecifier[0]);
    }
}
