package com.codengine.reactive.algoritmos

import com.codengine.`complexidade-algoritimos`.findElementInThreeArrays
import com.codengine.`complexidade-algoritimos`.findElementInVectorOfInts
import com.codengine.`complexidade-algoritimos`.findElementsInThoArrays
import com.codengine.generateAraayOfRandomNumbers
import org.junit.Before
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
//@WebFluxTest(controllers = arrayOf(LinkController::class))
class ComplexidadeAlgoritmosTest {


    private var numberArray = intArrayOf(0, 1, 2, 3, 4, 5, 5, 6, 7, 8, 1532, 9, 45, 78, 1596, -18)
    private var elementsArray = intArrayOf(-8, 1, 2, 3, 4, 1532, 36, 89, 45, 123, 97, 456, 23, 12, 389, 324, 1596)
    private var randomNumbersArray = generateAraayOfRandomNumbers(2532)
    @Before
    fun setUp() {
    }

    @Test
    @Order(1)
    fun shouldGenerateRandomNumbersArray() {
        var randomNumbersArray = generateAraayOfRandomNumbers(100)
        assert(randomNumbersArray.size == 100)
    }

    @Test
    @Order(2)
    fun algoritmoN1Test() {

        var testResult = findElementInVectorOfInts(numberArray, 5)
        println(testResult)
    }

    @Test
    @Order(3)
    fun algoritmoN2Test() {

        var testResult = findElementsInThoArrays(numberArray, elementsArray)
        testResult?.forEach { n -> println(n) }
    }

    @Test
    @Order(4)
    fun algoritmoN3Test() {

        var testResult = findElementInThreeArrays(numberArray, elementsArray, randomNumbersArray)
        testResult?.forEach { n -> println(n) }
    }
}