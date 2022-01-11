package com.santaclose.app.sample.repository

import com.santaclose.app.sample.resolver.dto.SampleDto
import com.santaclose.lib.entity.sample.Sample
import com.santaclose.lib.entity.sample.type.SampleStatus
import io.kotest.assertions.arrow.core.shouldBeSome
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
internal class SampleAppRepositoryTest @Autowired constructor(
    private val sampleAppRepository: SampleAppRepository
) {
    @Test
    fun `정상적으로 데이터를 생성한다`() {
        // given
        val sample = Sample(name = "name", price = 123, status = SampleStatus.CLOSE)
        sampleAppRepository.save(sample)

        // when
        val findSample = sampleAppRepository.findByPrice(sample.price)

        // then
        findSample shouldBeSome with(sample) {
            SampleDto(name, price, status)
        }
    }
}
