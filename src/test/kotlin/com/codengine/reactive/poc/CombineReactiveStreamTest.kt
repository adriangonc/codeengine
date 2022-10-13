package com.codengine.reactive.poc

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier
import java.time.Duration

class CombineReactiveStreamTest {

    private val fluxString: Flux<String> = Flux.just("A", "B", "C", "D")
    private val fluxNumber: Flux<String> = Flux.just("1", "2", "3", "4")

    @Test
    fun combineUsingMerge() {
        val combinedFlux = Flux.merge(fluxString, fluxNumber)

        StepVerifier.create(combinedFlux.log())
            .expectNext("A", "B", "C", "D", "1", "2", "3", "4")
            .verifyComplete()
    }

    @Test
    fun combineUsingMergeWithDelay() {
        val combinedFlux = Flux.merge(
            fluxString.delayElements(Duration.ofNanos(500)), fluxNumber.delayElements(Duration.ofNanos(500))
        )
        //merge não garante a ordenação dos elementos
        StepVerifier.create(combinedFlux.log())
            .expectNextCount(8)
            .verifyComplete()
    }

    @Test
    fun combineUsingConcat() {
        //concat mantem a ordenação dos elementos, porém e mais lento que o merge
        val combinedFlux = Flux.concat(
            fluxString.delayElements(Duration.ofNanos(500)), fluxNumber.delayElements(Duration.ofNanos(500))
        )

        StepVerifier.create(combinedFlux.log())
            .expectNext("A", "B", "C", "D", "1", "2", "3", "4")
            .verifyComplete()
    }

    @Test
    fun combineUsingZip() {
        // zip cria uma tupla como resultado: Ex "A1"
        val combinedFlux = Flux.zip(
            fluxString, fluxNumber
        )

        val resultFlux = combinedFlux.map { it.t1 + it.t2 }

        StepVerifier.create(resultFlux.log())
            .expectNext("A1","B2","C3","D4")
            .verifyComplete()
    }

}