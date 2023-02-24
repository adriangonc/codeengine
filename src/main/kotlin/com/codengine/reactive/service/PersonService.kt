package com.codengine.reactive.service

import com.codengine.reactive.model.Employee
import com.codengine.reactive.model.Person
import com.codengine.reactive.repository.PersonRepository
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class PersonService(
    @Autowired
        val personRepository: PersonRepository,
        private val rabbitTemplate: RabbitTemplate,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun savePersonFromQueue(person: Person) : Person? {
        try {
            log.info("Saving person to database...")

            //print("Saved person: $savedPerson")
            return personRepository.save(person).block()
        } catch (e : Exception) {
            println(e.message)
        }
        return null
    }

    fun savePerson(person: Person) : Mono<Person>? {
        try {
            log.info("Saving person to database...")
            return personRepository.save(person)
        } catch (e : Exception) {
            println(e.message)
        }
        return null
    }

    fun deletePerson(personId: String): Mono<Void> {
        return personRepository.deleteById(personId)
    }

    fun findAllPersons(): Flux<Person>{
        log.info("Finding all persons!")
        return personRepository.findAll()
    }

    fun sendMessage(
        exchange: String,
        routingKey: String,
        message: Person
    ) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message)
    }
}