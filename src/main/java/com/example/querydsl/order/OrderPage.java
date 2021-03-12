package com.example.querydsl.order;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.PathBuilderFactory;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class OrderPage {

    protected final List<OrderSpecifier<?>> orderSpecifiers;
    private final EntityManager entityManager;
    private final Class<?> returnTypeClass;

    protected OrderPage(EntityManager entityManager, Class<?> returnTypeClass) {
        this.orderSpecifiers = new ArrayList<>();
        this.entityManager = entityManager;
        this.returnTypeClass = returnTypeClass;
    }

    public <T> PageImpl<T> getPageImpl(JPQLQuery<T> query, PageOrderVo pageOrderVo) {
        long totalCount = query.fetchCount();

        JPQLQuery<T> applyOrderQuery = applyOrderQuery(query, pageOrderVo.getSorts());
        return getPageImplByPage(applyOrderQuery, pageOrderVo, totalCount);
    }

    private <T> JPQLQuery<T> applyOrderQuery(JPQLQuery<T> query, List<SortVo> sorts) {
        if (sorts == null || sorts.isEmpty()) {
            return query;
        }

        return query.orderBy(getOrder(sorts));
    }

    private Optional<Pageable> getPageable(Integer page, Integer pageSize) {
        if (page == null || pageSize == null) {
            return Optional.empty();
        }

        return Optional.of(PageRequest.of(page, pageSize));
    }

    private <T> PageImpl<T> getPageImplByPage(JPQLQuery<T> query, PageOrderVo pageOrderVo, long totalCount) {
        Optional<Pageable> optionalPageable = getPageable(pageOrderVo.getPage(), pageOrderVo.getPageSize());
        if (optionalPageable.isPresent()) {
            List<T> queryResults = getQuerydsl(returnTypeClass).applyPagination(optionalPageable.get(), query).fetch();
            return new PageImpl<>(queryResults, optionalPageable.get(), totalCount);
        }

        return new PageImpl<>(query.fetch());
    }

    private Querydsl getQuerydsl(Class<?> clazz) {
        PathBuilder<?> builder = new PathBuilderFactory().create(clazz);
        return new Querydsl(entityManager, builder);
    }

    protected void addOrderSpecifier(SortVo sortVo, ComparableExpressionBase<?> comparableExpressionBase) {
        if (sortVo.equalsAsc()) {
            this.orderSpecifiers.add(comparableExpressionBase.asc());
        } else if (sortVo.equalsDesc()) {
            this.orderSpecifiers.add(comparableExpressionBase.desc());
        }
    }

    protected abstract OrderSpecifier[] getOrder(List<SortVo> sorts);
}
