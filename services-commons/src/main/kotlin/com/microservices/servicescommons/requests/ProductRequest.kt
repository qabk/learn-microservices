package com.microservices.servicescommons.requests


import java.math.BigDecimal
import java.time.Instant

data class ProductRequest (
        val name: String?,
        val description: String?,
        val price: Long?,
        val validFrom: Instant?,
        val validTo: Instant?,
        val currency: String?,
        val quantity: Int?
)