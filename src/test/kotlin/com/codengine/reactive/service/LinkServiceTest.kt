package com.codengine.reactive.service

import io.kotlintest.matchers.collections.startWith
import io.kotlintest.matchers.startWith
import org.junit.jupiter.api.Test
import reactor.test.StepVerifier

class LinkServiceTest {
    private val baseUrl = "http://test.com/"
    private val linkService = LinkService(baseUrl)

    private val link = "https://google.com.br"
    private val shortenedLink = "https://shorlk"

    @Test
    fun shortensLinkTest(){
        StepVerifier
            .create(linkService.shortenedLink(link))
            .expectNextMatches{link -> link.startsWith(baseUrl)}
            .expectComplete()
            .verify()

    }

}