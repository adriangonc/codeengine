package com.codengine.reactive.service

class MathOperations {
    fun calculateSquareRoot(n: Int ): String {
        for (i: Int in 1..n){
            if(i*i == n) return "A raiz quadrada de $n e igual a $i" else if(i*i > n) break
        }
        return "O numero $n nao possui uma raiz quadrada exata!"
    }
}