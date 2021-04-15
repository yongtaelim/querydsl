package com.sellermill.products.goods.repository.test

import com.sellermill.products.goods.domain.entity.test.ReqYongMongoTest
import com.sellermill.products.goods.domain.entity.test.YongMongoTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.test.context.TestConstructor

/**
 * Created by LYT to 2021/04/09
 */
@SpringBootTest
//@DataMongoTest
//@Import(value = [TestMongoDbConfig::class])
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class YongTestRepositoryTest(
    private val yongTestRepository: YongTestRepository
) {

    @Test
    fun insertTest() {
        // Given
        val yongMongoTest = YongMongoTest(username = "yong", password = "1234", address = "강남", phone = "010-1234-5678")

        // When
        val newYongMongoTest = yongTestRepository.save(yongMongoTest)

        // Then
        assertThat(newYongMongoTest).isNotNull
    }

    @Test
    fun searchAll_test() {
        // Given


        // When
        val searchAll = yongTestRepository.searchAll()


        // Then
        assertThat(searchAll).hasSize(1)
    }

    @Test
    fun save_test() {
        // Given
        val yongMongoTest = mutableListOf(
            YongMongoTest(username = "yong1", password = "1234", address = "강남1", phone = "010-1234-5670"),
            YongMongoTest(username = "yong2", password = "1234", address = "강남2", phone = "010-1234-5671"),
            YongMongoTest(username = "yong3", password = "1234", address = "강남3", phone = "010-1234-5672"),
            YongMongoTest(username = "yong4", password = "1234", address = "강남4", phone = "010-1234-5673"),
            YongMongoTest(username = "yong5", password = "1234", address = "강남5", phone = "010-1234-5674"),
            YongMongoTest(username = "yong6", password = "1234", address = "강남6", phone = "010-1234-5676"),
            YongMongoTest(username = "yong7", password = "1234", address = "강남7", phone = "010-1234-5677"),

        )

        // When
        yongTestRepository.saveAll(yongMongoTest)

        // Then
    }

    @Test
    @DisplayName("리스트 정보 가져오기 테스트")
    fun search_list_include_where() {
        // Given
        val username = "yong1"


        // When
        val response = yongTestRepository.findByUsername(username)
        val mongoTemplateResponse = yongTestRepository.searchOneByUsername(username)


        // Then
        assertThat(response).hasSize(1)
        assertThat(mongoTemplateResponse).hasSize(1)

        assertThat(response[0].username).isEqualTo(mongoTemplateResponse[0].username)
    }

    @Test
    @DisplayName("단일 정보 가져오기 테스트, MongoRepository, MongoTemplate 방법")
    fun search_one_include_where() {
        // Given
        val username = "yong1"


        // When
        val response = yongTestRepository.findFirstByUsername(username)
        val mongoTemplateResponse = yongTestRepository.searchByUsername(username)


        // Then
        assertThat(response.username).isEqualTo(username)
        assertThat(mongoTemplateResponse?.username).isEqualTo(username)
    }

    @Test
    @DisplayName("Dynamic 조건절 리스트 조회 테스트")
    fun find_by_dynamic_where() {
        // Given
        val username = "yong1"
        val address = "강남1"
        val password = null
        val request = ReqYongMongoTest(username = username, address = address, password = password)

        // When
        val response = yongTestRepository.searchDynamicQuery(request)

        // Then
        assertThat(response).hasSize(1)
        assertThat(response[0].username).isEqualTo(username)
        assertThat(response[0].address).isEqualTo(address)
    }

    @Test
    @DisplayName("page 적용 테스트")
    fun find_by_pageable() {
        // Given
        val page = 0
        val size = 2
        val order = Sort.Order.desc("username")
        val pageable = generatePageable(page = page, size = size, order)

        // When
        val response = yongTestRepository.searchByPageable(pageable)

        // Then
        assertThat(response).isNotNull
        assertThat(response.content).hasSize(2)
        assertThat(response.content[0].username).isEqualTo("yong7")
//        assertThat(response.totalPages).isEqualTo(5)
    }

    private fun generatePageable(page: Int, size: Int, vararg order: Sort.Order): PageRequest {
        val sort = Sort.by(order.asList())
        return PageRequest.of(page, size, sort)
    }
}