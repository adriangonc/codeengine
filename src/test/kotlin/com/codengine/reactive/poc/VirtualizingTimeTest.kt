package com.codengine.reactive.poc

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier
import reactor.test.scheduler.VirtualTimeScheduler
import java.time.Duration

class VirtualizingTimeTest {
    @Test
    fun `flux test without virtual time scheduler`() {
        val flux = Flux.interval(Duration.ofMillis(250)).take(3)

        StepVerifier.create(flux.log()).expectNext(0,1,2).verifyComplete()
    }

    @Test
    fun `flux test with virtual time scheduler`() {
        VirtualTimeScheduler.getOrSet()

        val flux = Flux.interval(Duration.ofMillis(1000)).take(3)

        StepVerifier.withVirtualTime { flux.log() }
            .thenAwait(Duration.ofSeconds(10))
            .expectNext(0,1,2)
            .verifyComplete()
    }
}