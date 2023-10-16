package com.codengine.reactive.algoritmos

import com.codengine.`complexidade-algoritimos`.findElementInArrayOfArrays
import com.codengine.`complexidade-algoritimos`.findElementInVectorOfInts
import org.junit.Before
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
//@WebFluxTest(controllers = arrayOf(LinkController::class))
class ComplexidadeAlgoritmosTest {


    private var numberArray = intArrayOf(0, 1, 2, 3, 4, 5, 5, 6, 7, 8, 1532, 9)
    private var elementsArray = intArrayOf(-8, 1, 2, 3, 4, 1532)

    @Before
    fun setUp() {
            }

    @Test
    fun algoritmoN1Test() {

        var testResult = findElementInVectorOfInts(numberArray, 5)
        println(testResult)
    }

    @Test
    fun algoritmoN2Test() {

        var testResult = findElementInArrayOfArrays(numberArray, elementsArray)
        testResult?.forEach { n -> println(n) }
    }

}