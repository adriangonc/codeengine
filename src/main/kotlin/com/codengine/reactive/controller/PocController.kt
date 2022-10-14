package com.codengine.reactive.controller

import com.codengine.reactive.service.MathOperations
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration
import java.util.stream.Stream

@RestController
class PocController(private val mathOperation : MathOperations) {

    //Blocking
    @GetMapping("/flux/numbers")
    fun getNumbers() : Flux<Int> {
        return mathOperation.getNumbers().delayElements(Duration.ofMillis(250)).log()
    }
    //Non blocking
    @GetMapping("/flux/numbers/stream", produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    fun getNumbersStream() : Flux<Int> {
        return mathOperation.getNumbers().delayElements(Duration.ofMillis(200)).log()
    }
    //Non blocking
    @GetMapping("/flux/numbers/primes/{number}", produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    fun getPrimeNumbersStream(@PathVariable number: Int): Flux<Stream<Int>> {
        return mathOperation.findPrimeNumbersFilter(number)
    }

}