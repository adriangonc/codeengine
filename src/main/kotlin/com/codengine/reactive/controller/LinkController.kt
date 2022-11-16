package com.codengine.reactive.controller

import com.codengine.reactive.service.LinkService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/link")
class LinkController(
    private val linkService: LinkService
) {

    @PostMapping
    fun createShortLink(@RequestBody request : CreateLinkRequest) : Mono<CreateLinkRespose> {
        return linkService.shortenedLink(request.link).map { link -> CreateLinkRespose(link) }
    }

}

data class CreateLinkRequest(val link: String)

data class CreateLinkRespose(val shortenedLink: String)
