package com.codengine.reactive.math

import com.codengine.reactive.service.MathOperations
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MathOperationsTest {

    private val mathOperations = MathOperations()

    @Test
    fun squareRootTest(){
        val result = mathOperations.calculateSquareRoot(169)
        println(result)
        assertThat(result).contains("13")
    }

    @Test
    fun squareRootWrongTest(){
        val result = mathOperations.calculateSquareRoot(1532)
        println(result)
        assertThat(result).contains("nao possui")
    }

}