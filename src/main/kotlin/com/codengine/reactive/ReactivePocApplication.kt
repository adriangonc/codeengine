package com.codengine.reactive

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReactivePocApplication

fun main(args: Array<String>) {
	runApplication<ReactivePocApplication>(*args)
}
