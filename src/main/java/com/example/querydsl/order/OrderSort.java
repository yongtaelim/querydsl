package com.example.querydsl.order;

import lombok.Getter;

@Getter
public enum OrderSort {
    ASC("asc"),
    DESC("desc");

    private String name;

    OrderSort(String name) {
        this.name = name;
    }
}
