package com.codengine.reactive.algoritmos

import com.codengine.`complexidade-algoritimos`.findElementIn3Arrays
import com.codengine.`complexidade-algoritimos`.findElementInArrayOfArrays
import com.codengine.`complexidade-algoritimos`.findElementInVectorOfInts
import org.junit.Before
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
//@WebFluxTest(controllers = arrayOf(LinkController::class))
class ComplexidadeAlgoritmosTest {


    private var numberArray = intArrayOf(0, 1, 2, 3, 4, 5, 5, 6, 7, 8, 1532, 9, 45, 78)
    private var elementsArray = intArrayOf(-8, 1, 2, 3, 4, 1532, 36, 89, 45, 123, 97, 456, 23, 12, 389, 324, 1596)
    private var elementsArray3 = intArrayOf(-18, 19, -2, 38, 44, 1532, 36, 89, 45, 123, 97, 456, 23, 12, 389, 324, 1596)

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

    @Test
    fun algoritmoN3Test() {

        var testResult = findElementIn3Arrays(numberArray, elementsArray, elementsArray3)
        testResult?.forEach { n -> println(n) }
    }
}