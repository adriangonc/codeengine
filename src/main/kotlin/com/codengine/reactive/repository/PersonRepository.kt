package com.codengine.reactive.repository

import com.codengine.reactive.model.Person
import lombok.extern.slf4j.Slf4j
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Slf4j
@Repository
interface PersonRepository : ReactiveMongoRepository<Person, String> {
    fun save(person: Person): Mono<Person>
}