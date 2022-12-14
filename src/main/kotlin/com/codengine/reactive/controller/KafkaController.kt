package com.codengine.reactive.controller

import com.codengine.reactive.model.Employee
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.ResponseEntity
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/kafka")
class KafkaController(
    @Autowired
    private val kafkaTemplate: KafkaTemplate<String, Any>
) {

    @PostMapping("/employee")
    fun sendMessageToKafka(@RequestBody employee: Employee): HttpEntity<Any?>{
        kafkaTemplate.send("topic-test-1", (employee.toString()))
        return ResponseEntity.ok().build()
    }

}