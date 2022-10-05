package com.codengine.reactive.poc

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier

class FilterTest {
    private val cityList = listOf("Ipatinga", "BH", "Uberlandia", "Sao Paulo", "Passa dez")

    @Test
    fun filterSmallNameCitiesTest(){
        val fluxOfCities = Flux.fromIterable(cityList)
        val filteredCities = fluxOfCities.filter { city -> city.length <= 2}

        StepVerifier.create(filteredCities).expectNext("BH").verifyComplete()
    }

    @Test
    fun filterCitiesNameThatStartWithITest(){
        val fluxOfCities = Flux.fromIterable(cityList)
        val filteredCities = fluxOfCities.filter { city -> city.startsWith("I")}

        StepVerifier.create(filteredCities).expectNext("Ipatinga").verifyComplete()
    }
}