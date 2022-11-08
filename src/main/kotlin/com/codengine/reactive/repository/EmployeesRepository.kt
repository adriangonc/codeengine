package com.codengine.reactive.repository

import com.codengine.reactive.model.Employee
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface EmployeesRepository : ReactiveMongoRepository<Employee, String> {

    fun findByDepartment(department: String): Flux<Employee>

}