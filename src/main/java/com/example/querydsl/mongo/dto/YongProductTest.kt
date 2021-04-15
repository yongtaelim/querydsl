package com.sellermill.products.goods.domain.entity.test

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.time.Instant

/**
 * Created by LYT to 2021/04/12
 */
@Document(collection = "yongProduct")
class YongProductTest(
    var name: String,
    var sequence: Int,

    @Field("yong_item")
    var yongItem: YongProductItemTest,

    @Field("created_at")
    var createdAt: Instant? = Instant.now(),

    @Field("yong_options")
    var yongOptions: Array<String> = arrayOf()
)