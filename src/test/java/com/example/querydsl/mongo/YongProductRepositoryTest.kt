package com.sellermill.products.goods.repository.test

import com.sellermill.products.goods.domain.entity.test.YongProductItemTest
import com.sellermill.products.goods.domain.entity.test.YongProductTest
import org.assertj.core.api.Assertions.assertThat
import org.bson.types.ObjectId
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.TestConstructor
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

/**
 * Created by LYT to 2021/04/12
 */
@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class YongProductRepositoryTest(
    val yongProductRepository: YongProductRepository
) {

    @Test
    @DisplayName("save test")
    @BeforeEach
    fun mongodb_save_test() {
        // Given
        yongProductRepository.deleteAll()

        val documents = mutableListOf(
            YongProductTest(name = "yong-product1", sequence = 1, YongProductItemTest(name = "yong-product-item1", count = 100, option = "Red"), createdAt= Instant.now(), yongOptions = arrayOf("Red", "Yellow")),
            YongProductTest(name = "yong-product2", sequence = 2, YongProductItemTest(name = "yong-product-item2", count = 100, option = "Green"), createdAt= Instant.now(), yongOptions = arrayOf("Red", "Green")),
            YongProductTest(name = "yong-product3", sequence = 3, YongProductItemTest(name = "yong-product-item3", count = 100, option = "Red, Green"), yongOptions = arrayOf("Green", "Yellow")),
            YongProductTest(name = "yong-product4", sequence = 4, YongProductItemTest(name = "yong-product-item4", count = 100, option = "Red, Yellow"), yongOptions = arrayOf("1", "2")),
            YongProductTest(name = "yong-product5", sequence = 5, YongProductItemTest(name = "yong-product-item5", count = 100, option = "Red"), yongOptions = arrayOf("2", "4")),
            YongProductTest(name = "yong-product6", sequence = 6, YongProductItemTest(name = "yong-product-item6", count = 100, option = "Red"), yongOptions = arrayOf("a", "b")),
            YongProductTest(name = "yong-product7", sequence = 7, YongProductItemTest(name = "yong-product-item7", count = 100, option = "Red"), yongOptions = arrayOf("b", "c")),
            YongProductTest(name = "yong-product8", sequence = 8, YongProductItemTest(name = "yong-product-item8", count = 100, option = "Red"), yongOptions = arrayOf("d", "e")),
            YongProductTest(name = "yong-product9", sequence = 9, YongProductItemTest(name = "yong-product-item9", count = 100, option = "Red"), yongOptions = arrayOf("10", "Yellow")),
            YongProductTest(name = "yong-product10", sequence = 10, YongProductItemTest(name = "yong-product-item10", count = 100, option = "Red"), yongOptions = arrayOf("22", "Yellow")),
            YongProductTest(name = "yong-product11", sequence = 11, YongProductItemTest(name = "yong-product-item11", count = 100, option = "Red"), yongOptions = arrayOf("Red", "h4"))
        )

        // When
        yongProductRepository.saveAll(documents)

        // Then

    }

    @Test
    @DisplayName("delete test")
    fun mongodb_delete_test() {
        yongProductRepository.deleteAll()
    }

    @Test
    @Transactional
    @DisplayName("update test from mongo repository")
    fun update_from_repository_test() {
        // Given
        val product = yongProductRepository.findByIdOrNull(ObjectId("6073f95efb3cfd16cb127175"))
        product?.name = "yongtae!!"


        // When
        val newProduct = yongProductRepository.save(product!!)


        // Then
        assertThat(newProduct.name).isEqualTo(product.name)
    }

    @Test
    @Transactional
    @DisplayName("update test from mongo template")
    fun update_from_mongo_template() {
        // Given
        val name = "yong-product10"

        // When
        val response = yongProductRepository.updateFirst(name)


        // Then
        assertThat(response.matchedCount).isEqualTo(1L)
        assertThat(response.modifiedCount).isEqualTo(1L)
    }

    @Test
    @DisplayName("컬렉션 내부 객체의 count 증가 from mongo repository")
    fun update_inc_from_repository_test() {
        // Given
        val product = yongProductRepository.findByIdOrNull(ObjectId("6073f95efb3cfd16cb127175"))
        product?.yongItem?.count = product?.yongItem?.count?.plus(1)!!

        // When
        yongProductRepository.save(product!!)

        // Then
        assertThat(product?.yongItem.count).isEqualTo(101)
    }

    @Test
    @DisplayName("컬렉션 내부 객체의 count 증가 from mongoTemplate")
    fun update_inc_from_mongo_template_test() {
        // Given
        val name = "yong-product3"


        // When
        val response = yongProductRepository.incrementItemCount(name)

        // Then
        assertThat(response.matchedCount).isEqualTo(1L)
        assertThat(response.modifiedCount).isEqualTo(1L)
    }

    @Test
    @DisplayName("컬렉션 내 배열에 값 추가하기")
    fun update_add_in_array() {
        // Given
        val name = "yong-product1"
        val option = "White"

        // When
        val response = yongProductRepository.addOptionsArray(name, option)

        // Then
        assertThat(response.matchedCount).isEqualTo(1L)
        assertThat(response.modifiedCount).isEqualTo(1L)
    }
}