package com.codengine.`complexidade-algoritimos`

fun findElementInVectorOfInts(numberArray: IntArray, element: Number): Number? {
    for (n in 0..numberArray.size) { // O(N)
        if (n == element) return element;
    }
    return null
    //Complexidade O(N)
}

fun findElementInArrayOfArrays(numberArray: IntArray, elementArray: IntArray): Array<Number> {
    var elementsInTwoArrays: Array<Number> = arrayOf()
    for (n in numberArray.indices) { // O(N)
        for (j in elementArray.indices) { // O(N)
            if (numberArray[n] == elementArray[j]) {
                elementsInTwoArrays = elementsInTwoArrays.plus(elementArray[j])
            }
        }
    }

    return elementsInTwoArrays
    //Complexidade O(N²)
}

