package com.codengine.reactive.service

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class EventService(
    @Autowired
    private val kafkaTemplate: KafkaTemplate<String, Any>
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun sendEvent(event : Any, topicName : String){
        try {
            kafkaTemplate.send(topicName, (event.toString()))
            log.info("Event send to broker: \n ${event.toString()}")
        } catch (e: Exception){
            log.error("Erro ao enviar evento: \n ${event.toString()} ")
        }
    }

    fun sendEventAvro(event : Any, topicName : String){
        try {
            kafkaTemplate.send(topicName, (event.toString()))
            log.info("Event send to broker: \n ${event.toString()}")
        } catch (e: Exception){
            log.error("Erro ao enviar evento: \n ${event.toString()} ")
        }
    }
}