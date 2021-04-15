package com.sellermill.products.goods.repository.test

import com.mongodb.client.MongoCollection
import com.mongodb.client.result.InsertManyResult
import com.mongodb.client.result.UpdateResult
import com.sellermill.products.goods.domain.entity.test.YongProductTest
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Query.query
import org.springframework.data.mongodb.core.query.Update
import java.time.Instant

/**
 * Created by LYT to 2021/04/12
 */
class YongProductQueryRepositoryImpl(
    val mongoTemplate: MongoTemplate
): YongProductQueryRepository {

    override fun updateFirst(name: String): UpdateResult {

        val query = Query()
        query.addCriteria(Criteria.where("name").`is`(name))

        val update = Update()
        update.set("sequence", 100)

        return mongoTemplate.updateFirst(query, update, YongProductTest::class.java)
    }

    override fun incrementItemCount(name: String): UpdateResult {
        val query = Query()
        query.addCriteria(Criteria.where("name").`is`(name))

        val update = Update()
        update.inc("item.count", 1)

        return mongoTemplate.updateFirst(query, update, YongProductTest::class.java)
    }

    override fun addOptionsArray(name: String, option: String): UpdateResult {
        val query = Query()
        query.addCriteria(Criteria.where("name").`is`(name))

        val update = Update()
        update.addToSet("yong_options", option)

        return mongoTemplate.updateFirst(query, update, YongProductTest::class.java)
    }
}