package com.example.querydsl.store.entity;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @ColumnTransformer(read = "CreatePrefix(account_number)", write = "CreatePrefix(?)")
    private String name;
    private String address;

    @Builder
    public Store(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
