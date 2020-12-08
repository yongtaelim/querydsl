package com.example.querydsl.staff.repository;

import com.example.querydsl.staff.entity.Staff;
import com.example.querydsl.staff.vo.StaffVo;
import com.example.querydsl.util.PagingUtil;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.querydsl.staff.entity.QStaff.staff;

@RequiredArgsConstructor
public class CustomizedStaffRepositoryImpl implements CustomizedStaffRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final PagingUtil pagingUtil;


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

    public PageImpl<StaffVo> findStaffsByNamePaging(String name, Pageable pageable) {
        JPQLQuery<StaffVo> query = jpaQueryFactory
                .select(Projections.fields(StaffVo.class,
                        staff.id
                        , staff.age
                        , staff.name
                ))
                .from(staff)
                .where(staff.name.eq(name));

        return pagingUtil.getPageImpl(pageable, query, Staff.class);
    }
}
