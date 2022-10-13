package com.codengine.reactive.poc

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.SignalType
import reactor.test.StepVerifier
import java.time.Duration

class ErrorHandlingTest {

    private val fluxOfLetters = Flux.just("A","B","C")

    @Test
    fun doOnErrorTest(){
        val flux = fluxOfLetters
            .concatWith(Flux.error(RuntimeException("Some error for test")))
            .doOnError{
                error -> println("Log de error: $error !!!")
            }

        StepVerifier.create(flux.log())
            .expectNext("A","B","C")
            .expectError()
            .verify()
    }

    @Test
    fun onErrorReturnTest(){
        val flux = fluxOfLetters
            .concatWith(Flux.error(RuntimeException("Some error for test")))
            .onErrorReturn("Default value on error")

        StepVerifier.create(flux.log())
            .expectNext("A","B","C")
            .expectNext("Default value on error")
            .verifyComplete()
    }

    @Test
    fun onErrorResumeTest(){
        val flux = fluxOfLetters
            .concatWith(Flux.error(RuntimeException("Some error for test")))
            .onErrorResume {
                println("Some error occurred ${it.message}")

                Flux.just("E")
            }

        StepVerifier.create(flux.log())
            .expectNext("A","B","C")
            .expectNext("E")
            .verifyComplete()
    }


    @Test
    fun onErrorMapTest(){
        val flux = fluxOfLetters
            .concatWith(Flux.error(RuntimeException("Database exception")))
            .onErrorMap {
                when (it.message) {
                    "Database exception" -> CustomerException("Ocorreu um erro ao consultar dados!")
                    else -> it
                }
            }

        StepVerifier.create(flux.log())
            .expectNext("A","B","C")
            .expectErrorMessage("Ocorreu um erro ao consultar dados!")
            .verify()
    }

    @Test
    fun doFinalyTest(){
        val flux = fluxOfLetters
            .delayElements(Duration.ofMillis(100))
            .doFinally{
                when{
                    it == SignalType.CANCEL -> println("Do on Cancel!")
                    it == SignalType.ON_COMPLETE -> println("Do on Complete!")
                    it == SignalType.ON_ERROR -> println("Do on Error!")
                }
            }.log()
            .take(2)

        StepVerifier.create(flux).expectNext("A","B")
            .thenCancel()
            .verify()
    }
}

class CustomerException(errorMessage: String) : Throwable(errorMessage)
