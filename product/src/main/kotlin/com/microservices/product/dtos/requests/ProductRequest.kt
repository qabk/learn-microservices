package com.microservices.product.dtos.requests

import java.math.BigDecimal
import java.time.Instant
import java.util.Currency

data class ProductRequest (
        val name: String?,
        val description: String?,
        val price: BigDecimal?,
        val validFrom: Instant?,
        val validTo: Instant?,
        val currency: String?
)