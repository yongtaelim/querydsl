package com.example.querydsl.staff.repository;

import com.example.querydsl.staff.entity.Staff;
import com.example.querydsl.staff.vo.StaffVo;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomizedStaffRepository {
    List<Staff> searchAll();
    StaffVo search(Long id);
    Staff findStaffById(Long id);

    PageImpl<StaffVo> findStaffsByNamePaging(String name, Pageable pageable);
}
