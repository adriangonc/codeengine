package com.codengine.reactive.poc

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers.parallel
import reactor.kotlin.core.publisher.toMono
import reactor.test.StepVerifier

class FlapMapTest {

    private val studentIds = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")

    @Test
    fun flatMapStudents() {
        val studentName = Flux.fromIterable(studentIds)
            .flatMap { id -> getStudentsInformation(id) }
            .log()

        StepVerifier.create(studentName).expectNextCount(10).verifyComplete()
    }

    @Test
    fun flatMapStudentsParallelScheduler() {
        val studentName = Flux.fromIterable(studentIds)
            .window(getAvailableCpus()) //Quantidade de threads que serão executadas
            .flatMap { identifiers ->
                identifiers.flatMap { id ->
                    getStudentsInformation(id)
                }.subscribeOn(parallel()) //Permite a execução da operação em multiplas threads
            }.log()

        StepVerifier.create(studentName).expectNextCount(10).verifyComplete()
    }

    @Test
    fun flatMapStudentsParallelSchedulerOrdered() {
        val studentName = Flux.fromIterable(studentIds)
            .window(getAvailableCpus()) //Quantidade de threads que serão executadas
            .flatMapSequential { identifiers -> //Mantem a ordenação porem aumenta o tempo de execução
                identifiers.flatMap { id ->
                    getStudentsInformation(id)
                }.subscribeOn(parallel()) //Permite a execução da operação em multiplas threads
            }.log()

        StepVerifier.create(studentName).expectNextCount(10).verifyComplete()
    }

    private fun getStudentsInformation(id: String?): Mono<String> {
        //Simula busca no banco ou chamada de outras api's
        val students = mapOf(
            "1" to "Adriano",
            "2" to "Sara",
            "3" to "João",
            "4" to "Maria",
            "5" to "José",
            "6" to "Adalto",
            "7" to "Renato",
            "8" to "Felipe",
            "9" to "Thales",
            "10" to "Patrik",
            "11" to "Jim",
            "12" to "Kerigan"
        )
        Thread.sleep(250)
        return students.getOrDefault(id, "Student not found!").toMono()
    }
    
    private fun getAvailableCpus(): Int {
        //var cpuCount = Runtime.getRuntime().availableProcessors()
        var cpuCount = 4
        println("Available CPUs = $cpuCount")
        return cpuCount
    }
}