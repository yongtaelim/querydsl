package com.example.querydsl.store.entity;


import com.example.querydsl.staff.entity.Staff;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @ColumnTransformer(read = "CreatePrefix(account_number)", write = "CreatePrefix(?)")
    private String name;
    private String address;
    private Integer age;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Staff> staffs;

    @Builder
    public Store(Long id, String name, String address, Integer age, List<Staff> staffs) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
        this.staffs = staffs;
    }
}
