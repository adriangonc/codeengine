package com.codengine.reactive.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
//import org.springframework.data.redis.core.RedisHash

@Document("employees")
//@RedisHash("Employees")
data class Employee(
    @Id
    val id: String?,
    val name: String,
    val department: String
) {
    override fun toString(): String {
        return "{ id: ${this.id}, name: ${this.name}, department: ${this.department} }"
    }
}