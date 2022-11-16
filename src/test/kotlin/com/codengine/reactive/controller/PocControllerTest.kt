package com.codengine.reactive.controller

import com.codengine.reactive.service.MathOperationsService
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Flux

@RunWith(SpringRunner::class)
@WebFluxTest(controllers = arrayOf(PocController::class))
class PocControllerTest(
    @Autowired
    val webTestClient: WebTestClient
) {

    @MockBean
    private lateinit var mathOperations: MathOperationsService

    @Test
    fun `flux API test`() {
        BDDMockito.given(mathOperations.getNumbers())
            .willReturn(Flux.just(2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97))

        webTestClient.get()
            .uri("/flux/numbers/stream")
            .exchange()
            .expectStatus()
            .is2xxSuccessful
    }

}