package com.codengine.reactive.config

import org.springframework.amqp.core.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QueueConfig {
    @Bean
    fun firstQueue(): Queue { //Define a fila
        return QueueBuilder.durable("FIRST-QUEUE-BASIC").build()
    }

    @Bean
    fun directExchange(): Exchange { //Define o exchange
        return ExchangeBuilder
            .directExchange("DIRECT-EXCHANGE-BASIC")
            .durable(true)
            .build()
    }

    @Bean
    fun firstDirectBinding(fistQueue: Queue) : Binding { //Define o binding
        return BindingBuilder
            .bind(fistQueue)
            .to(directExchange())
            .with("TO-FIRST-QUEUE")
            .noargs()
    }
}