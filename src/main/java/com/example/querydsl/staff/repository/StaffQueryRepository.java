package com.example.querydsl.staff.repository;

import com.example.querydsl.staff.entity.Staff;
import com.example.querydsl.staff.vo.StaffEtcVo;
import com.example.querydsl.staff.vo.StaffVo;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StaffQueryRepository {
    List<Staff> searchAll();
    StaffVo search(Long id);
    Staff findStaffById(Long id);

    PageImpl<StaffVo> findStaffsByNamePaging(String name, Pageable pageable);

    StaffEtcVo findStaffAndEtcOption(String id);

    Staff dynamicQuery(String name);

    Boolean findExist(String name);

    Boolean findLimitOneInsteadOfExist(String name);
}
