package com.codengine.reactive.service

import com.codengine.reactive.model.Person
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import reactor.core.publisher.Mono
import java.time.LocalDate
import java.time.Month

@ExtendWith(MockKExtension::class)
class PersonServiceTest {

    @MockK
    private lateinit var personService: PersonService

    @BeforeEach
    fun setup() = MockKAnnotations.init(this)

    @Test
    fun `should save person`(){
        //Arrange
        var person = Person("Adriano", 2016, LocalDate.of(1988, Month.APRIL, 15), true)
        every { personService.savePerson(person) } returns Mono.just(person)


        // Act
        var savedPerson = personService.savePerson(person)

        //Assert
        if (savedPerson != null) {
            assertEquals(savedPerson.block().toString(), person.toString())
        }
    }

}