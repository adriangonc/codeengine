package com.codengine.reactive.infra

import com.codengine.reactive.model.Person
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.stereotype.Service

@Service
class PersonConsumer (
        private val messageConverter: MessageConverter
    )
{
    private val log = LoggerFactory.getLogger(javaClass)

    @RabbitListener(queues = ["EMPLOYEE-QUEUE"])
    fun receiveMessageFromJsonQueue(message: Message) {
        log.info("receive message from ${message.messageProperties.consumerQueue}")
        //val person = messageConverter.fromMessage(message) as Person
        log.info("body $message")
    }
}