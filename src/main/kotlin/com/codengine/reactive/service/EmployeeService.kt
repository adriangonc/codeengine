package com.codengine.reactive.service

import com.codengine.reactive.model.Employee
import com.codengine.reactive.repository.EmployeesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class EmployeeService(
    @Autowired
    val employeeRepository: EmployeesRepository,

    @Autowired
    val cacheService: RedisCacheService
) {


    fun getEmployeeNames(department: String): Flux<Employee> {
        println("Geting employees from database!")
        return employeeRepository.findByDepartment(department)
    }

    fun save(employee : Employee): Mono<Employee> {
        cacheService.save(employee.id.toString(), employee.toString())
        return employeeRepository.save(employee)
    }
}