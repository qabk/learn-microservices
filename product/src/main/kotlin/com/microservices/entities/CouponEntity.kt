package com.microservices.entities

import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.Instant

@Document(value = "coupon")

data class CouponEntity (
        val id: String?,
        val name: String?,
        val description: String?,
        val validFrom: Instant?,
        val validTo: Instant?,
        val discountPercent: Double?,
        val discountValue: BigDecimal,
        val discountType: DiscountType,
)

enum class DiscountType {
    PRODUCT, ORDER
}