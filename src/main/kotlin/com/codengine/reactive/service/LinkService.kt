package com.codengine.reactive.service

import org.apache.commons.lang3.RandomStringUtils
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class LinkService {

    fun shortenedLink(link: String) : Mono<String> {
        val randomKey = RandomStringUtils.randomAlphabetic(7)
        //TODO Save
        return Mono.just(randomKey)
    }

}
