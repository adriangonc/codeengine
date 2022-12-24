package com.codengine.reactive.model

import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
@Document("person")
data class Person(
    val name: String,

    val collageCompletedYear: Int?,

    val bornAt: LocalDate,

    val active: Boolean
) {
    override fun toString(): String {
        return "{ name: ${this.name}, " +
                "collageCompletedYear: ${this.collageCompletedYear}, " +
                "bornAt: ${this.bornAt}, " +
                "active: ${this.active} }"
    }
}