package com.example.querydsl.store.entity;


import com.example.querydsl.staff.entity.Staff;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //    @ColumnTransformer(read = "CreatePrefix(account_number)", write = "CreatePrefix(?)")
    private String name;
    private String address;

//    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private List<Staff> staffs = new ArrayList<>();

    @Builder
    public Store(Long id, String name, String address, List<Staff> staffs) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.staffs = staffs;
    }
}
