package com.codengine.reactive.infra

import com.codengine.reactive.model.Person
import com.codengine.reactive.repository.PersonRepository
import com.codengine.reactive.service.PersonService
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PersonConsumer (
        private val messageConverter: MessageConverter,
        private val personService: PersonService,
        @Autowired
        val personRepository: PersonRepository
    )
{
    private val log = LoggerFactory.getLogger(javaClass)

    @RabbitListener(queues = ["PERSON-QUEUE"])
    fun receiveMessageFromJsonQueue(person: Person) {
        log.info("receive message from ${person.toString()}")
        //val person = messageConverter.fromMessage(message) as Person
        log.info("body $person")
        personService.savePersonFromQueue(person)
    }

    //TODO implementar DLQ
}