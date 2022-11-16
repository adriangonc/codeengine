package com.codengine.reactive.controller

import com.codengine.reactive.model.Employee
import com.codengine.reactive.repository.EmployeesRepository
import com.codengine.reactive.service.EmployeeService
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RunWith(SpringRunner::class)
@WebFluxTest(controllers = arrayOf(EmployeeController::class))
class EmployeeControllerTest(
    @Autowired
    val webTestClient : WebTestClient
) {

    @MockBean
    lateinit var employeeService: EmployeeService

    @MockBean
    lateinit var employeesRepository: EmployeesRepository

    @Test
    fun `list all employees test`(){
        BDDMockito.given(employeeService.getEmployee("1532"))
            .willReturn(Mono.just(Employee("1532","Adriano","IT")) )

        BDDMockito.given(employeesRepository.findById("1532"))
            .willReturn(Mono.just(Employee("1532","Adriano","IT")) )

        webTestClient.get()
            .uri("/v1/employees/1532")
            .exchange()
            .expectStatus()
            .is2xxSuccessful
    }

}