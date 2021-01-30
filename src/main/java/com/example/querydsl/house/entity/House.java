package com.example.querydsl.house.entity;

import com.example.querydsl.staff.entity.Staff;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@DynamicInsert
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "house")
    private List<Staff> staffs = new ArrayList<>();

    @Builder
    public House(Long id, String name, List<Staff> staffs) {
        this.id = id;
        this.name = name;
        if (staffs != null) {
            this.staffs = staffs;
        }
    }

    public void addStaff(Staff staff) {
        this.staffs.add(staff);
        staff.changeHouse(this);
    }
}
