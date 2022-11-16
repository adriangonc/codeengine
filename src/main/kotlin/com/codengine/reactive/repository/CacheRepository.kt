package com.codengine.reactive.repository

import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.time.Duration

@Slf4j
@Repository
class CacheRepository(
    @Autowired
    val reactiveRedisTemplate : ReactiveRedisTemplate<String, String>
) {

    val ttl = 60L

    fun saveCache(key: String, value: String): Mono<Boolean> {
        return reactiveRedisTemplate.opsForValue().set(key, value) //Salva valor no cache
            .then(reactiveRedisTemplate.expire(key, Duration.ofSeconds(ttl))) //Seta tempo de expiração do cache
            .doOnError {
                println("Erro ao salvar no cache ${it.stackTraceToString()}")
                Mono.just(false)
            }
    }

    fun getCache(key: String): Mono<String> {
        return reactiveRedisTemplate.opsForValue()
            .get(key) //Pega valor no cache
            .doOnError {
                println("Erro ao salvar no cache ${it.stackTraceToString()}")
                Mono.empty<String>()
            }
    }

    fun existsForKey(key: String): Mono<Boolean> {
        return reactiveRedisTemplate
            .hasKey(key)
            .doOnError {
                println("Erro ao consultar cache ${it.stackTraceToString()}")
                Mono.just(false)
            }
    }

}