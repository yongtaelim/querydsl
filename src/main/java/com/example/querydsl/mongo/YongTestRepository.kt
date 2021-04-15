package com.sellermill.products.goods.repository.test

import com.sellermill.products.goods.domain.entity.test.YongMongoTest
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Created by LYT to 2021/04/09
 */
interface YongTestRepository: MongoRepository<YongMongoTest, ObjectId>, YongTestQueryRepository {
    fun findByUsername(username: String) : MutableList<YongMongoTest>

    fun findFirstByUsername(username: String) : YongMongoTest
}
