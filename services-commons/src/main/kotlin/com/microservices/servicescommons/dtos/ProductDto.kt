package com.microservices.servicescommons.dtos

import java.math.BigDecimal
import java.time.Instant

data class ProductDto(
        val id: String?,
        val name: String?,
        val description: String?,
        val price: BigDecimal?,
        val validFrom: Instant?,
        val validTo: Instant?,
        val currency:  String?,
)