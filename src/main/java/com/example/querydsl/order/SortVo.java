package com.example.querydsl.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SortVo {
    private String key;
    private OrderSort orderSort;

    public boolean equalsAsc() {
        return OrderSort.ASC.equals(this.orderSort);
    }

    public boolean equalsDesc() {
        return OrderSort.DESC.equals(this.orderSort);
    }
}
