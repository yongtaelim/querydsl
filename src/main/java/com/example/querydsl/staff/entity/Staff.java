package com.example.querydsl.staff.entity;

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

    @Builder
    public Staff(Long id, String name, Integer age, Store store) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.store = store;
    }

    public void changeName(final String name) {
        this.name = name;
    }
}
