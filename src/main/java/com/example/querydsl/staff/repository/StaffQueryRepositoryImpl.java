package com.example.querydsl.staff.repository;

import com.example.querydsl.staff.entity.Staff;
import com.example.querydsl.staff.vo.StaffEtcVo;
import com.example.querydsl.staff.vo.StaffVo;
import com.example.querydsl.util.PagingUtil;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.example.querydsl.staff.entity.QStaff.staff;
import static com.example.querydsl.store.entity.QStore.store;

@RequiredArgsConstructor
public class StaffQueryRepositoryImpl implements StaffQueryRepository {
    private final JPAQueryFactory queryFactory;
    private final PagingUtil pagingUtil;

    @Override
    public List<Staff> searchAll() {
        return queryFactory
                .selectFrom(staff)
                .fetch();
    }

    @Override
    public StaffVo search(Long id) {
        return queryFactory
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
        return queryFactory
                .selectFrom(staff)
                .where(staff.id.eq(id))
                .fetchOne();
    }

    public PageImpl<StaffVo> findStaffsByNamePaging(String name, Pageable pageable) {
        JPQLQuery<StaffVo> query = queryFactory
                .select(Projections.fields(StaffVo.class,
                        staff.id
                        , staff.age
                        , staff.name
                ))
                .from(staff)
                .where(staff.name.eq(name));

        return pagingUtil.getPageImpl(pageable, query, Staff.class);
    }

    @Override
    public StaffEtcVo findStaffAndEtcOption(String name) {
        return queryFactory
                .select(Projections.fields(StaffEtcVo.class,
                        staff,
                        store.address
                        ))
                .from(staff)
                    .join(store.staffs)
                .where(staff.name.eq(name))
                .fetchFirst();
    }

    @Override
public Staff dynamicQuery(String name) {
    return queryFactory
            .selectFrom(staff)
            .where(eqName(name))
            .fetchOne();
}

    @Override
public Boolean findExist(String name) {
    BooleanExpression exists = queryFactory
            .selectOne()
            .from(staff)
            .where(staff.name.eq(name))
            .fetchAll()
            .exists();

    return queryFactory
            .select(exists)
            .from(staff)
            .fetchOne();
}

    @Override
    public Boolean findLimitOneInsteadOfExist(String name) {
        Integer fetchFirst = queryFactory
                .selectOne()
                .from(staff)
                .where(staff.name.eq(name))
                .fetchFirst();          // limit(1).fetchOne()

        return fetchFirst != null;      // 값이 없으면 0이 아니라 null 반환
    }

    private BooleanExpression eqName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }

        return staff.name.eq(name);
    }
}
