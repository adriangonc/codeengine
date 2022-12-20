package com.codengine.reactive.service

import com.codengine.reactive.model.Link
import com.codengine.reactive.repository.LinkRepository
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class LinkService(
    val linkRepository : LinkRepository,

    @Value("\${app.baseUrl}")
    private val baseUrl : String
) {

    fun shortenedLink(link: String) : Mono<String> {
        val randomKey = RandomStringUtils.randomAlphabetic(7)
        return linkRepository.save(Link(link, randomKey))
            .map { result -> baseUrl + result.key }
        //return Mono.just(baseUrl + randomKey)
    }

}
