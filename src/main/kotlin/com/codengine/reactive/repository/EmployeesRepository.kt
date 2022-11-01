package com.codengine.reactive.repository

import com.codengine.reactive.model.Employee
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeesRepository : ReactiveMongoRepository<Employee, String>