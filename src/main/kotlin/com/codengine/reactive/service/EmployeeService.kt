package com.codengine.reactive.service

import com.codengine.reactive.model.Employee
import com.codengine.reactive.repository.EmployeesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class EmployeeService(
    @Autowired
    val employeeRepository: EmployeesRepository
) {
    fun getEmployeeNames(department: String): Flux<Employee> {
        return employeeRepository.findByDepartment(department)
    }
}