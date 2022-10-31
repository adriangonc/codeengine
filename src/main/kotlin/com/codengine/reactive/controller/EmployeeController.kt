package com.codengine.reactive.controller

import com.codengine.reactive.model.Employee
import com.codengine.reactive.repository.EmployessRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("v1/employees")
class EmployeeController(
    @Autowired
    val employeeRepository: EmployessRepository
) {

    @GetMapping
    fun getAllEmployees(): Flux<Employee> {
        return employeeRepository.findAll()
        //TODO criar imagem docker do mongo db
    }

}