package com.codengine.reactive.controller

import com.codengine.reactive.service.LinkService
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono

@RunWith(SpringRunner::class)
@WebFluxTest(controllers = arrayOf(LinkController::class))
class LinkControllerTest(
    @Autowired
    val webTestClient : WebTestClient

) {

    @MockBean
    private lateinit var linkService: LinkService

    val link = "https://google.com.br"
    val shortenedLink = "https://shorlk"

    @Test
    fun shorterLink(){
        //Mockito.`when`(linkService.shortenedLink("https://google.com.br")).thenReturn(Mono.just("http://localhost:8080/1532"))
        BDDMockito.given(linkService.shortenedLink(link)).willReturn(Mono.just(shortenedLink))

        webTestClient.post()
            .uri("/link")
            .contentType(MediaType.APPLICATION_JSON)
            .syncBody("{\"link\":\"https://google.com.br\"}")
            .exchange()
            .expectStatus()
            .is2xxSuccessful()
            .expectBody()
            .jsonPath("$.shortenedLink").isEqualTo(shortenedLink)

    }

}