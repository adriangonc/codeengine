package com.codengine.reactive.poc

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux

class MapTest {
    @Test
    fun mapTest(){
        Flux.range(15, 32)
            .map { it * 2 }
            .subscribe { println(it) }
    }
    
    @Test
    fun evenNumberMap() {
        Flux.range(1, 19).map { it * it}
            .filter { it % 2 == 0}
            .subscribe { println(it) }
    }
}