package com.codengine.reactive.poc

import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

class MonoTest {
    @Test
    fun monoTest() {
        Mono.just("Adr").log()
            .subscribe { data -> println(data)}
    }

    @Test
    fun monoTestError() {
        Mono.error<Exception>(Exception("Error test")).log()
            .doOnError { error -> println("Error: $error") }
            .subscribe()
    }

    @Test
    fun verifyMonoTest() {
        val mono = Mono.just("Adriano").log()

        StepVerifier.create(mono).expectNext("Adriano").verifyComplete()
    }
    
}