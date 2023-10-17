package com.codengine

import kotlin.random.Random


fun generateRandomNumber(): Int {
    return Random.nextInt(0, 1600)
}

fun generateAraayOfRandomNumbers(number: Int): Array<Number> {
    var randomNumbers: Array<Number> = arrayOf()
    for (i in 0 until number) {
        randomNumbers = randomNumbers.plus(generateRandomNumber())
    }
    return randomNumbers
}
