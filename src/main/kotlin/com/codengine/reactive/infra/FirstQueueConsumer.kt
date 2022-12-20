package com.codengine.reactive.infra

import org.slf4j.LoggerFactory
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class FirstQueueConsumer {
    private val log = LoggerFactory.getLogger(javaClass)

    @RabbitListener(queues = ["FIRST-QUEUE-BASIC"])
    fun bodyAsStringFromFirstQueue(message: Message) {
        val bodyAsString = message.body?.let { String(it) } ?: ""
        log.info("body $bodyAsString")
    }
}