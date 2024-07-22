package com.microservices.servicescommons.dtos

import java.time.Instant

data class CustomerDto (
        val id: String?,
        val firstName: String?,
        val lastName: String?,
        val dateOfBirth: Instant?,
        val address: String?,
        val email: String?
)