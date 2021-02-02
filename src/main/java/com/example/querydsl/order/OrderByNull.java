package com.example.querydsl.order;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.NullExpression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;

public class OrderByNull extends OrderSpecifier {
    public static final OrderByNull DEFAULT = new OrderByNull();

    public OrderByNull(Order order, Expression target, NullHandling nullhandling) {
        super(order, target, nullhandling);
    }

    public OrderByNull(Order order, Expression target) {
        super(order, target);
    }

    private OrderByNull() {
        super(Order.ASC, NullExpression.DEFAULT, NullHandling.Default);
    }
}
