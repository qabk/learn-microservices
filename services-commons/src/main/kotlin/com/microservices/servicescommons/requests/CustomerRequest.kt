package com.microservices.servicescommons.requests

import java.time.Instant

data class CustomerRequest (
        val firstName: String?,
        val lastName: String?,
        val dateOfBirth: Instant?,
        val address: String?,
        val email: String?
)