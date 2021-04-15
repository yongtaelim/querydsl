package com.sellermill.products.goods.domain.entity.test

import org.springframework.data.mongodb.core.mapping.Document

/**
 * Created by LYT to 2021/04/09
 */
@Document("users")
class YongMongoTest(
    val username: String,
    val password: String,
    val address: String,
    val phone: String
)