package com.codengine.reactive.poc

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import java.time.Duration

class FluxTest {

    private val testListOfElements = listOf("A","B","C","D","E")
    private val testFluxOfNumbers = Flux.just(1,2,3)
    private val testFluxOfElements = Flux.just("A","B","C","D","E")

    @Test
    fun testFlux(){
        val flux = Flux.just(testFluxOfElements).log().subscribe { data -> println(data)}
        assertThat(flux)
    }

    @Test
    fun testFluxWithError(){
        val flux = Flux.just(testFluxOfElements)
            .log()//.concatWith(Flux.error(RuntimeException("Erro test occurred!")))
            .subscribe ({ data -> println(data)},
                        {error -> println("Find error: $error")},
                        {println("Successful executed!")})
        assertThat(flux)
    }

    @Test
    fun testFluxWithIterable(){
        Flux.fromIterable(testListOfElements)
            .log()
            .subscribe { data -> println(data)}
    }

    @Test
    fun testFluxWithRange(){
        Flux.range(4,5)
            .subscribe { data -> println(data)}
    }

    @Test
    fun testFluxIntervalAndTake(){
        Flux.interval(Duration.ofMillis(2))
            .log()
            .take(15) //Pega a quantidade especificada de elementos de um flux infinito
            .subscribe { data -> println(data)}
        Thread.sleep(2000)
    }

    @Test
    fun testFluxRequest(){
        Flux.range(1, 1532)
            .log()
            .subscribe ({ data -> println(data)}, {}, {}, {subscription -> subscription.request(34)} )
        Thread.sleep(2000)
    }

    @Test
    fun testFluxErrorHandling(){
        val flux = Flux.just(testFluxOfElements)
            .concatWith(Flux.error(RuntimeException("Error - Something strange happened!")))
            .doOnError { println(testFluxOfNumbers.subscribe{ d -> println(d) }) }
            .log()
            .subscribe { data -> println(data)}

        assertThat(flux).isNotIn(testFluxOfElements)
    }

}

