package com.codengine.reactive.controller

import com.codengine.reactive.model.Employee
import com.codengine.reactive.model.Person
import com.codengine.reactive.service.EmployeeService
import com.codengine.reactive.service.EventService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/kafka")
class EventController(
    @Autowired
    private val eventService: EventService
) {

    @PostMapping("/event/{topicName}")
    fun sendMessageToKafka(@RequestBody employee: Employee, @PathVariable topicName : String): HttpEntity<Any?>{
        return try {
            eventService.sendEvent(employee, topicName)
            ResponseEntity.ok().build()
        } catch (e: Exception) {
            ResponseEntity.internalServerError().build()
        }
    }

    @PostMapping("/person")
    fun sendPersonToKafka(@RequestBody person: Person): HttpEntity<Any?>{
        //kafkaTemplate.send("topic-person", (person.toString()))
        return ResponseEntity.ok().build()
    }

}