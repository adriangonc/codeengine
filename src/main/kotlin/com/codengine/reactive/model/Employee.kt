package com.codengine.reactive.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Employee(
    @Id
    val id: String?,
    val name: String,
    val department: String
)