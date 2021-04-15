package com.sellermill.products.goods.repository.test

import com.sellermill.products.goods.domain.entity.test.ReqYongMongoTest
import com.sellermill.products.goods.domain.entity.test.YongMongoTest
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.CriteriaDefinition
import org.springframework.data.mongodb.core.query.Query

/**
 * Created by LYT to 2021/04/09
 */

class YongTestQueryRepositoryImpl(
    private val mongoTemplate: MongoTemplate
) : YongTestQueryRepository {
    override fun searchAll() =
        mongoTemplate.findAll(YongMongoTest::class.java)

    override fun searchOneByUsername(username: String): MutableList<YongMongoTest> {
        val query = Query(Criteria.where("username").`is`(username))
        return mongoTemplate.find(query, YongMongoTest::class.java)
    }

    override fun searchByUsername(username: String): YongMongoTest? {
        val query = Query(Criteria.where("username").`is`(username))
        return mongoTemplate.findOne(query, YongMongoTest::class.java)
    }

    override fun searchDynamicQuery(request: ReqYongMongoTest): MutableList<YongMongoTest> {
        val query = Query()
        query.apply {
            eqUsername(this, username = request.username)
            eqAddress(this, address = request.address)
            eqPassword(this, password = request.password)
        }

        return mongoTemplate.find(query, YongMongoTest::class.java)
    }

    override fun searchByPageable(pageable: Pageable): PageImpl<YongMongoTest> {
        val query = Query()

        val totalCount = mongoTemplate.count(query, YongMongoTest::class.java)
        val results = mongoTemplate.find(query.with(pageable), YongMongoTest::class.java)

        return PageImpl(results, pageable, totalCount)
    }

    private fun eqUsername(query: Query, username: String?) {
        username?.let { query.addCriteria(Criteria.where("username").`is`(username)) }
    }

    private fun eqAddress(query: Query, address: String?) {
        address?.let { query.addCriteria(Criteria.where("address").`is`(address)) }
    }

    private fun eqPassword(query: Query, password: String?)  {
        password?.let { query.addCriteria(Criteria.where("password").`is`(password)) }
    }

}