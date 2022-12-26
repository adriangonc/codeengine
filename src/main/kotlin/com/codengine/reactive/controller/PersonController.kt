package com.codengine.reactive.controller

import com.codengine.reactive.model.Person
import com.codengine.reactive.repository.PersonRepository
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux

@RestController
@RequestMapping("v1/person")
class PersonController(
    val personRepository: PersonRepository,
    private val rabbitTemplate: RabbitTemplate
) {
    private val log = LoggerFactory.getLogger(javaClass)


    @GetMapping
    fun getAllPersons(): Flux<Person> {
        return personRepository.findAll()
    }

    @PostMapping("/send/{exchange}/{routingKey}")
    fun postPersonOnExchange(
        @PathVariable exchange: String,
        @PathVariable routingKey: String,
        @RequestBody message: Person
    ): HttpEntity<Any?> {
        log.info("sending message $message")
        rabbitTemplate.convertAndSend(exchange, routingKey, message)
        return ResponseEntity.ok().build()
    }

}
