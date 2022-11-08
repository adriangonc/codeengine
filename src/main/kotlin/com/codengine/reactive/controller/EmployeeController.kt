package com.codengine.reactive.controller

import com.codengine.reactive.model.Employee
import com.codengine.reactive.repository.EmployeesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("v1/employees")
class EmployeeController(
    @Autowired
    val employeeRepository: EmployeesRepository
) {

    @GetMapping
    fun getAllEmployees(): Flux<Employee> {
        return employeeRepository.findAll()
    }

    @GetMapping("{id}")
    fun getEmployeeById(@PathVariable id: String): Mono<Employee> {
        return employeeRepository.findById(id)
    }

    @PostMapping
    fun insertEmployee(@RequestBody employee: Employee): Mono<Employee> {
        return employeeRepository.save(employee)
    }

    @PutMapping("/update")
    fun updateEmployee(@RequestBody employee: Employee): Mono<Employee> {
        return employeeRepository.save(employee)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteEmployee(@PathVariable id: String): Mono<Void> {
        return employeeRepository.deleteById(id)
    }


}