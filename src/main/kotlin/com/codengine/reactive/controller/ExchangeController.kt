package com.codengine.reactive.controller

import com.codengine.reactive.model.Person
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("v1/exchange")
class ExchangeController(
    private val rabbitTemplate: RabbitTemplate
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping("{exchange}/{routingKey}")
    fun postOnExchange(@PathVariable exchange: String, @PathVariable routingKey: String, @RequestBody message: String): Mono<Any?>{
        log.info("Sending message: $message")
        rabbitTemplate.convertAndSend(exchange, routingKey, message) //Send message to queue
        return Mono.just("Message sended to queue!")
    }

    @PostMapping("person/{exchange}/{routingKey}")
    fun postPersonOnExchange(@PathVariable exchange: String, @PathVariable routingKey: String, @RequestBody person: Person): HttpEntity<Any?> {
        rabbitTemplate.convertAndSend(exchange, routingKey, person)
        log.info("Sending message to exchange: $exchange, routingKey: $routingKey, message: $person ")
        return ResponseEntity.ok().build()
    }
}