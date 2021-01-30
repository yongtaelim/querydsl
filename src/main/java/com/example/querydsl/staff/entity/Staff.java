package com.example.querydsl.staff.entity;

import com.example.querydsl.bag.entity.Bag;
import com.example.querydsl.house.entity.House;
import com.example.querydsl.store.entity.Store;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer age;
    private String lastName;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id", foreignKey = @ForeignKey(name = "fk_staff_store_id"))
    private Store store;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "house_id", foreignKey = @ForeignKey(name = "fk_staff_house_id"))
    private House house;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Bag bag;

    @Builder
    public Staff(Long id, String name, Integer age, String lastName, Store store, House house, Bag bag) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.lastName = lastName;
        this.store = store;
        this.house = house;
        this.bag = bag;
    }

    public void changeName(final String name) {
        this.name = name;
    }

    public void changeHouse(House house) {
        this.house = house;
    }
}
