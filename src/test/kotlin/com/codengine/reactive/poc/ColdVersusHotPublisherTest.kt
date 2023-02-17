package com.codengine.reactive.poc

import org.junit.jupiter.api.Test
import reactor.core.publisher.ConnectableFlux
import reactor.core.publisher.Flux
import java.time.Duration

class ColdVersusHotPublisherTest {
    private val fluxOfElements = Flux.just("A","B","C","D")

    @Test
    fun `cold publisher test`(){
        val flux = fluxOfElements
            .delayElements(Duration.ofMillis(500))

        flux.subscribe{ println("Subscriber 1: $it") } //Emite elementos desde o início
        Thread.sleep(550)

        flux.subscribe{ println("Subscriber 2: $it") } //Emite elementos desde o início
        Thread.sleep(400)
    }

    @Test
    fun `hot publisher test`(){
        val flux = fluxOfElements
            .delayElements(Duration.ofMillis(500))

        val connectableFlux : ConnectableFlux<String> = flux.publish()
        connectableFlux.connect()

        connectableFlux.subscribe{ println("Subscriber 1: $it") } //Emite elementos desde o início
        Thread.sleep(700)

        connectableFlux.subscribe{ println("Subscriber 2: $it") } //Emite elementos desde o início
        Thread.sleep(500)
    }
}