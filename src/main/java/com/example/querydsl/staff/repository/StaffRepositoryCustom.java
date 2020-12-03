package com.example.querydsl.staff.repository;

import com.example.querydsl.staff.entity.Staff;
import com.example.querydsl.staff.vo.StaffVo;

import java.util.List;

public interface StaffRepositoryCustom {
    List<Staff> searchAll();
    StaffVo search(Long id);
    Staff findStaffById(Long id);
}
