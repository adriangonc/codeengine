package com.codengine.reactive.repository

import com.codengine.reactive.model.Link
import lombok.extern.slf4j.Slf4j
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Slf4j
@Repository
interface LinkRepository : ReactiveMongoRepository<Link, String> {
    fun save(link: Link): Mono<Link>
}
