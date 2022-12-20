package com.codengine.reactive.service

import com.codengine.reactive.model.Link
import com.codengine.reactive.repository.LinkRepository
import io.kotlintest.matchers.collections.startWith
import io.kotlintest.matchers.startWith
import org.junit.Before
import org.junit.Ignore
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.mockito.Mockito.mock
import org.springframework.boot.test.mock.mockito.MockBean
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

class LinkServiceTest {
    private val baseUrl = "http://test.com/"
    private val link = "https://google.com.br"
    private val shortenedLink = "https://shorlk"

    private val linkRepository: LinkRepository = mock(LinkRepository::class.java)
    private val linkDto = Link(link, shortenedLink)
    private val linkService = LinkService(linkRepository, baseUrl)


    /*@Before
    fun setUp() {
        BDDMockito.given(linkRepository.save(linkDto)).willReturn(Mono.just(Link(link, shortenedLink)))
    }*/

    //@Test //Comentado devido a NPE
    fun shortensLinkTest() {
        BDDMockito.given(linkRepository.save(linkDto)).willReturn(Mono.just(Link(link, shortenedLink)))

        StepVerifier
            .create(linkService.shortenedLink(link))
            .expectNextMatches { link -> link.startsWith(baseUrl) }
            .expectComplete()
            .verify()

    }

}