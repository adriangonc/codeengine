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
)