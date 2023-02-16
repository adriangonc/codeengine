package com.codengine.reactive.controller

import com.codengine.reactive.model.Employee
import com.codengine.reactive.model.Person
import com.codengine.reactive.service.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/kafka")
class KafkaController(
    @Autowired
    private val employeeService: EmployeeService
) {

    @PostMapping("/employee")
    fun sendMessageToKafka(@RequestBody employee: Employee): HttpEntity<Any?>{
        return try {
            employeeService.sendEmployeeEvent(employee)
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