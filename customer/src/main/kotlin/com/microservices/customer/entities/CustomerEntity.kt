package com.microservices.customer.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
@Document(collection = "customer")
data class CustomerEntity (
        @Id
        val id: String? = null,
        val firstName: String?,
        val lastName: String?,
        val dateOfBirth: Instant?,
        val address: String?,
        val email: String?,
)