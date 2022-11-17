package com.codengine.reactive.service

import org.apache.commons.lang3.RandomStringUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class LinkService(
    @Value("\${app.baseUrl}")
    private val baseUrl : String
) {

    fun shortenedLink(link: String) : Mono<String> {
        val randomKey = RandomStringUtils.randomAlphabetic(7)
        //TODO Save
        return Mono.just(baseUrl + randomKey)
    }

}
