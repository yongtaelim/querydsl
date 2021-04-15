package com.sellermill.products.goods.repository.test

import com.sellermill.products.goods.domain.entity.test.YongProductTest
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Created by LYT to 2021/04/12
 */
interface YongProductRepository: MongoRepository<YongProductTest, ObjectId>, YongProductQueryRepository {
    fun findByName(name: String) : YongProductTest
}