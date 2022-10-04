package com.codengine.reactive.poc

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier

class FluxImplementationTest {
    private val testListOfElements = listOf("A","B","C","D","E")
    private val testFluxOfNumbers = Flux.just(1,2,3)
    private val testFluxOfElements = Flux.just("A","B","C","D","E")

    @Test
    fun verifyFluxCreation() {
        val flux = Flux.just(testListOfElements)
            .log()
        StepVerifier.create(flux).expectNext(testListOfElements).verifyComplete()
    }

    @Test
    fun verifyFluxCreationWhitError() {
        val flux = Flux.just("1", 2, "A")
            .concatWith(Flux.error(RuntimeException("Error occurred!")))
            .log()
        StepVerifier.create(flux)
            .expectNext("1",2, "A")
            .expectError(RuntimeException::class.java)
            .verify()
    }
}