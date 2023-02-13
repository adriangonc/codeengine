package com.codengine.reactive.service

import com.codengine.reactive.model.Person
import com.codengine.reactive.repository.PersonRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class PersonService(
        @Autowired
        val personRepository: PersonRepository
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun savePerson(person: Person) : Mono<Person>? {
        try {
            log.info("Saving person to database...")
            var savedPerson = personRepository.save(person)
            print("Saved person: $savedPerson")
            return savedPerson
        } catch (e : Exception) {
            println(e.message)
        }
        return null
    }

    fun deletePerson(personId: String): Mono<Void> {
        return personRepository.deleteById(personId)
    }

}