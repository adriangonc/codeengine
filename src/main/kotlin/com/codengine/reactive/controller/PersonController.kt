package com.codengine.reactive.controller

import com.codengine.reactive.model.Person
import com.codengine.reactive.repository.PersonRepository
import com.codengine.reactive.service.PersonService
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("v1/person")
class PersonController(
    val personRepository: PersonRepository,

    @Autowired
    private val personService: PersonService
) {
    private val log = LoggerFactory.getLogger(javaClass)


    @GetMapping
    fun getAllPersons(): Flux<Person> {
        return personService.findAllPersons()
    }

    @PostMapping
    fun postPerson(@RequestBody person: Person): Mono<Person>? {
        return personService.savePerson(person)
    }

    @PostMapping("/send/{exchange}/{routingKey}")
    fun postPersonOnExchange(
        @PathVariable exchange: String,
        @PathVariable routingKey: String,
        @RequestBody message: Person
    ): HttpEntity<Any?> {
        log.info("sending message $message")
        personService.sendMessage(exchange, routingKey, message)
        return ResponseEntity.ok().build()
    }

}
