package com.codengine.reactive.service

import com.codengine.reactive.repository.CacheRepository
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Slf4j
@Service
class RedisCacheService(
    @Autowired
    val cacheRepository: CacheRepository
) {

    fun save(key: String, value: String): Mono<String> {
        return cacheRepository
            .saveCache(key, value)
            .flatMap { saved ->
                run {
                    if (saved) {
                        println("Salvo no cache!")
                    } else {
                        println("Erro ao salvar no cache!")
                    }
                    Mono.just(value)
                }
            }
    }

    fun get(key: String): Mono<String> {
        return cacheRepository
            .getCache(key)
            .doOnNext { println("Retornando cache para a chave $key") }
    }

    fun exists(key: String): Mono<Boolean> {
        return cacheRepository
            .existsForKey(key)
            .doOnNext { println("Cache existe para a chave $key") }
    }

}
