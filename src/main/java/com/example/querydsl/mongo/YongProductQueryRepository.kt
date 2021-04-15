package com.sellermill.products.goods.repository.test

import com.mongodb.client.result.UpdateResult
import com.sellermill.products.goods.domain.entity.test.YongProductTest
import org.bson.types.ObjectId
import java.time.Instant

/**
 * Created by LYT to 2021/04/12
 */
interface YongProductQueryRepository {
    fun updateFirst(productName: String) : UpdateResult

    fun incrementItemCount(name: String) : UpdateResult

    fun addOptionsArray(name: String, option: String): UpdateResult
}