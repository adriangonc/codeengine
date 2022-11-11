package com.codengine.reactive.repository

import com.codengine.reactive.model.Employee
import lombok.extern.slf4j.Slf4j
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Slf4j
@Repository
interface EmployeesRepository : ReactiveMongoRepository<Employee, String> {

    fun findByDepartment(department: String): Flux<Employee>

}