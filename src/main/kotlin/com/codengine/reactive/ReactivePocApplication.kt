package com.codengine.reactive



import org.springframework.boot.runApplication
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class ReactivePocApplication {

	@Bean
	fun messageConverter(objectMapper: ObjectMapper): MessageConverter {
		return Jackson2JsonMessageConverter(objectMapper)
	}

}

fun main(args: Array<String>) {
	runApplication<ReactivePocApplication>(*args)
}
