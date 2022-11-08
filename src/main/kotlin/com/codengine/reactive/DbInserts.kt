package com.codengine.reactive

import com.codengine.reactive.model.Employee
import com.codengine.reactive.repository.EmployeesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class DbInserts(
    @Autowired
    val employeeRepository : EmployeesRepository,

    @Autowired
    val reactiveMongoOperations: ReactiveMongoOperations
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        if(isDbEmpty()) {
            dbSetup()
        } else {
            println("Database not empty!")
        }
    }

    private fun isDbEmpty(): Boolean {
        var employeeCont = employeeRepository.count()
        println("The data base has ${employeeCont.block()} employees!")
        return employeeCont.block()!! < 1L
    }

    private fun dbSetup() {
        val employees = employeesList.flatMap {
            employeeRepository.save(it)
        }

        reactiveMongoOperations.collectionExists(Employee::class.java)
            .flatMap {
                when(it) {
                    true -> reactiveMongoOperations.dropCollection(Employee::class.java)
                    false -> Mono.empty()
                }
            }
            .thenMany(reactiveMongoOperations.createCollection(Employee::class.java))
            .thenMany(employees)
            .thenMany(employeeRepository.findAll())
            .subscribe({println(it)}, {error -> println(error)}/*error consumer*/, {println(" : Initial data inserted in database!") })
    }

    val employeesList = Flux.just(
        Employee(null, "Adriano", "IT"),
        Employee(null, "Sara", "HR"),
        Employee(null, "Adalto", "EL"),
        Employee(null, "Renato", "GE"),
        Employee(null, "Felipe", "SE"),
        Employee(null, "Celso", "IT"),
        Employee(null, "João", "IT"),
        Employee(null, "Joabe", "IT"),
        Employee(null, "Lucas", "IT"),
        Employee(null, "Maria", "MG"),
        Employee(null, "Jose", "MG"),
        Employee(null, "Carlos", "IT"),
        Employee(null, "Jim", "IT"),
        Employee(null, "Zeratul", "IT")
    )
}