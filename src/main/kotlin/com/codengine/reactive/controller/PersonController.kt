package com.codengine.reactive.controller

import com.codengine.reactive.model.Person
import com.codengine.reactive.repository.PersonRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("v1/person/all")
class PersonController(
        val personRepository: PersonRepository
) {
    @GetMapping
    fun getAllPersons(): Flux<Person> {
        return personRepository.findAll()
    }

}
