package com.codengine.reactive.service

import com.codengine.reactive.model.Person
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessageListener
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.stereotype.Service

@Service
class PersonListener(
    private val messageConverter: MessageConverter
) : MessageListener {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun onMessage(message: Message?) {
        log.info("receive message from ${message?.messageProperties?.consumerQueue}")
        message?.let {
            val person: Person = messageConverter.fromMessage(it) as Person
            log.info("person $person")
            if (person.collageCompletedYear == null) {
                throw RuntimeException("Wrong data for person class")
            }
        } ?: log.warn("No message found")

    }
}