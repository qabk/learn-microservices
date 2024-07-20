package com.microservices.product.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.annotation.Collation
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.Instant
import java.util.Currency

@Document(collection = "product")
data class ProductEntity (
        @Id
        val id: String? = null,
        val name: String?,
        val description: String?,
        val price: BigDecimal?,
        val validFrom: Instant?,
        val validTo: Instant?,
        val currency: String?
)