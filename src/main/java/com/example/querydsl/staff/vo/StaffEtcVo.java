package com.example.querydsl.staff.vo;

import com.example.querydsl.staff.entity.Staff;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StaffEtcVo {

    @JsonUnwrapped
    private Staff staff;
    private String address;
}
