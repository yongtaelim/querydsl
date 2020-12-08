package com.example.querydsl.store.support;


import com.example.querydsl.staff.vo.StaffVo;
import com.example.querydsl.store.entity.Store;
import com.example.querydsl.store.vo.StoreVo;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.PathBuilderFactory;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static com.example.querydsl.staff.entity.QStaff.staff;
import static com.example.querydsl.store.entity.QStore.store;

@Repository
public class StoreRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;

    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     *
     * @param domainClass must not be {@literal null}.
     * @param entityManager
     */
    public StoreRepositorySupport(JPAQueryFactory jpaQueryFactory, EntityManager entityManager) {
        super(Store.class);
        this.jpaQueryFactory = jpaQueryFactory;
        this.entityManager = entityManager;
    }

    public StoreVo findByName(String name) {
        return jpaQueryFactory
                .select(Projections.fields(StoreVo.class,
                        store.id
                        , Expressions.stringTemplate("createPrefix({0})", store.name).as("name")
                        , store.address
                        ))
                .from(store)
                .where(store.name.eq(name))
                .limit(1L)
                .fetchOne();
    }

    public Store findOneByName(String name) {
        return jpaQueryFactory
                .selectFrom(store)
                .where(store.name.eq(name))
                .fetchOne();
    }

    /**
     * Entity 관계 매핑 되어 있는 경우
     * @param name
     * @return
     */
//    public List<StaffVo> findStaffsByName(String name) {
//        return jpaQueryFactory
//                .select(Projections.fields(StaffVo.class,
//                        staff.id
//                        , staff.age
//                        , staff.name
//                ))
//                .from(store)
//                .join(store.staff, staff)
//                .where(store.name.eq(name))
//                .fetch();
//    }

    /**
     * Entity 관계 매핑 되어 있지 않을 경우
     * @param name
     * @return
     */
    public List<StaffVo> findStaffsByName(String name) {
        return jpaQueryFactory
                .select(Projections.fields(StaffVo.class,
                        staff.id
                        , staff.age
                        , staff.name
                ))
                .from(store)
                .join(staff)
                    .on(store.id.eq(staff.storeId))
                .where(store.name.eq(name))
                .fetch();
    }

    @Transactional
    public Long deleteQuery(Long id) {
//        return jpaQueryFactory.delete(store)
//                .where(store.id.eq(id))
//                .execute();

        return delete(store)                // QuerydslRepositorySupport
                .where(store.id.eq(id))
                .execute();
    }

    @Transactional
    public Long updateQuery(Long id, String address) {
//        return jpaQueryFactory.update(store)
//                .where(store.id.eq(id))
//                .execute();
        return update(store)                // QuerydslRepositorySupport
                .set(store.address, address)
                .where(store.id.eq(id))
                .execute();
    }

    public PageImpl<StoreVo> findStoresByNamePaging(String name, Pageable pageable) {
        JPQLQuery<StoreVo> query = jpaQueryFactory
                .select(Projections.fields(StoreVo.class,
                        store.id
                        , store.name
                        , store.address
                ))
                .from(store)
                .where(store.name.eq(name));

        long totalCount = query.fetchCount();
        List<StoreVo> results = getQuerydsl().applyPagination(pageable, query).fetch();
        return new PageImpl<>(results, pageable, totalCount);
    }
}
