package com.codengine.reactive.service

import com.codengine.reactive.model.Employee
import com.codengine.reactive.repository.EmployeesRepository
import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class EmployeeService(
    @Autowired
    val employeeRepository: EmployeesRepository,

    @Autowired
    private val kafkaTemplate: KafkaTemplate<String, Any>,

    @Autowired
    val cacheService: RedisCacheService
) {


    fun getEmployeeNames(department: String): Flux<Employee> {
        println("Geting employees from database!")
        return employeeRepository.findByDepartment(department)
    }

    fun save(employee: Employee): Mono<Employee> {
        cacheService.save(employee.id.toString(), employee.toString())
        kafkaTemplate.send("employee-topic", (employee.toString()))
        return employeeRepository.save(employee)
    }

    fun getEmployee(id: String): Mono<Employee> {
        var employeeJson = Employee("", "", "")
        val employeeCache = cacheService.get(id).log()
        try {
            val employee = Mono.just(jsonToEmployee(employeeCache.toString()))
            println(employee)
            return if (!employee.block()?.id.isNullOrEmpty()) {
                Mono.just(employeeJson)
            } else {
                findEmployeeOnDatabase(id)
            }
        } catch (ex: Exception) {
            println(ex)
            return findEmployeeOnDatabase(id)
        }

    }

    private fun findEmployeeOnDatabase(id: String): Mono<Employee> {
        val employee = employeeRepository.findById(id)
        cacheService.save(id, employee.subscribe().toString())
        return employee
    }

    fun jsonToEmployee(employeeJson: String): Employee {
        println(employeeJson)
        if (employeeJson.equals(null)) {
            return Employee("", "", "")
        }
        val gson = Gson()
        return gson.fromJson(employeeJson, Employee::class.java)
    }

    fun sendEmployeeEvent(employee : Employee){
        kafkaTemplate.send("employee-topic", (employee.toString()))
    }
}