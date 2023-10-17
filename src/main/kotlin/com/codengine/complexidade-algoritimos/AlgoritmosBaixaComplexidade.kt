package com.codengine.`complexidade-algoritimos`

fun findElementInVectorOfInts(numberArray: IntArray, element: Number): Number? {
    for (n in 0..numberArray.size) { // O(N)
        if (n == element) return element;
    }
    return null
    //Complexidade O(N)
}

fun findElementsInThoArrays(numberArray: IntArray, elementArray: IntArray): Array<Number> {
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

fun findElementInThreeArrays(numberArray: IntArray, elementArray: IntArray, elementArray3: IntArray): IntArray {
    var elementsInTwoArrays: MutableSet<Int> = mutableSetOf()
    for (n in numberArray.indices) { // O(N)
        for (j in elementArray.indices) { // O(N)
            for(i in elementArray3.indices){
                if (numberArray[n] == elementArray[j]) {
                    elementsInTwoArrays.add(elementArray[j])
                    continue
                } else if (numberArray[n] == elementArray3[i]){
                    elementsInTwoArrays.add(elementArray3[i])
                }
            }
        }
    }

    return elementsInTwoArrays.toIntArray()
    //Complexidade O(N²)
}

