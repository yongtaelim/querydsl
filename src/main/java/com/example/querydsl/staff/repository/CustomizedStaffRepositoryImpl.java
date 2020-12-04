package com.example.querydsl.staff.repository;

import com.example.querydsl.staff.entity.Staff;
import com.example.querydsl.staff.vo.StaffVo;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.querydsl.staff.entity.QStaff.staff;

@RequiredArgsConstructor
public class CustomizedStaffRepositoryImpl implements CustomizedStaffRepository {
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<Staff> searchAll() {
        return jpaQueryFactory
                .selectFrom(staff)
                .fetch();
    }

    @Override
    public StaffVo search(Long id) {
        return jpaQueryFactory
                .select(Projections.fields(StaffVo.class,
                    staff.id,
                    staff.name
                ))
                .from(staff)
                .where(staff.id.eq(id))
                .fetchOne();
    }

    @Override
    public Staff findStaffById(Long id) {
        return jpaQueryFactory
                .selectFrom(staff)
                .where(staff.id.eq(id))
                .fetchOne();
    }
}
