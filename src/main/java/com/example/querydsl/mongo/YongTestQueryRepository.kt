package com.sellermill.products.goods.repository.test

import com.sellermill.products.goods.domain.entity.test.ReqYongMongoTest
import com.sellermill.products.goods.domain.entity.test.YongMongoTest
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

/**
 * Created by LYT to 2021/04/09
 */
interface YongTestQueryRepository {
    fun searchAll(): MutableList<YongMongoTest>

    fun searchOneByUsername(username: String): MutableList<YongMongoTest>

    fun searchByUsername(username: String): YongMongoTest?

    fun searchDynamicQuery(request: ReqYongMongoTest) : MutableList<YongMongoTest>

    fun searchByPageable(pageable: Pageable) : PageImpl<YongMongoTest>
}