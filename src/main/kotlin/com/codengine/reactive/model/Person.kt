package com.codengine.reactive.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document("person")
data class Person(
    @Id
    var id: String?,

    var name: String,

    val collageCompletedYear: Int?,

    val bornAt: LocalDate,

    val active: Boolean
) {
    override fun toString(): String {
        return "Person(id='$id', name='$name', collageCompletedYear=$collageCompletedYear, bornAt=$bornAt, active=$active)"
    }
}