package com.codengine.reactive.service

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.util.stream.Stream

@Service
class MathOperations {

    private var globalPrimeNumberList = listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97)

    fun calculateSquareRoot(n: Int ): String {
        for (i: Int in 1..n){
            if(i*i == n) return "A raiz quadrada de $n e igual a $i" else if(i*i > n) break
        }
        return "O numero $n nao possui uma raiz quadrada exata!"
    }

    fun findPrimeNumbers(n: Int): Flux<MutableList<Int>> {
        val primesList = mutableListOf<Int>()
        for (num in 2..n) {
            if ((2 until num).none { num % it == 0 })
                primesList.add(num)
        }
        return Flux.just(primesList).log()
    }

    fun findPrimeNumbersFilter(n: Int): Flux<Stream<Int>> {
        var primesList = if(globalPrimeNumberList.last() > n) {
            globalPrimeNumberList.filter {primes -> primes < n}
        } else {
            ((2..n).filter { num -> (2 until num).none { num % it == 0 } })
        }
        if(globalPrimeNumberList.size > primesList.size) {
            globalPrimeNumberList = primesList
        }
        return Flux.just(primesList.stream()).log()
        
    }

    fun getNumbers(): Flux<Int> {
        val numbers = Flux.just(1,10,20,30,40,50,60,70,80,90,100)
        return numbers
    }
}