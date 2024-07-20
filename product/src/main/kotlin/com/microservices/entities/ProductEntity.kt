package com.microservices.entities

import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.Instant

@Document(value = "product")
data class ProductEntity (
        val id: String?,
        val name: String?,
        val description: String?,
        val price: BigDecimal?,
        val validFrom: Instant?,
        val validTo: Instant?,
)